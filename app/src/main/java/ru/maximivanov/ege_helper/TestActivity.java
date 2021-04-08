package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class TestActivity extends AppCompatActivity {
    private Test test;
    private byte id;
    private int taskAmount = -1;
    private Handler handler;
    private byte taskNum;
    private ArrayList<TaskFragment> taskArr;
    private boolean isCommon;
    private TestThread thread;
    private FinishButtonFragment buttonFragment;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        thread = new TestThread();
        id = getIntent().getByteExtra("subject", (byte) 0);
        isCommon = getIntent().getBooleanExtra("isCommon", true);
        if (isCommon) {
            commonTestFun();
        }
        else {
            taskNum = getIntent().getByteExtra("taskNum", (byte) 0);
            oneTaskTestFun();
        }
    }

    private void setHandler() {
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                RelativeLayout rl = findViewById(R.id.test_layout);
                rl.removeView(findViewById(R.id.loading));
                if (isCommon) {
                    test.commonTaskSet(getSupportFragmentManager());
                }
                else {
                    test.setTaskNum(taskNum);
                    test.oneTaskSet(getSupportFragmentManager());
                }
                buttonFragment.getButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 1; i <= taskAmount; ++i) {
                            String userAnswer = taskArr.get(i).getUserAnswer();
                            String correctAnswer = test.getTasks().get(i-1).getAnswer();
                            test.getTasks().get(i-1).userAnswer = userAnswer;
                            if (userAnswer.equals(correctAnswer)) {
                                test.incrementTestScore();
                                test.getTasks().get(i - 1).isRight = true;
                                SubjectsList.getSubject(id).ansScoreInc(i-1);
                            }
                            else {
                                int oldJ = -1;
                                boolean isRight = false;
                                for (int j = 0; j <= correctAnswer.length(); ++j) {
                                    if (j == correctAnswer.length() || correctAnswer.charAt(j) == '|') {
                                        if (userAnswer.equals(correctAnswer
                                                .substring(oldJ+1, j))) {
                                            test.incrementTestScore();
                                            test.getTasks().get(i - 1).isRight = true;
                                            SubjectsList.getSubject(id).tasksAnswersScore[i-1]++;
                                            isRight = true;
                                            break;
                                        }
                                        oldJ = j;
                                    }
                                }
                                if (!isRight) {
                                    test.getTasks().get(i - 1).isRight = false;
                                    SubjectsList.getSubject(id).ansScoreDec(i-1);
                                }
                            }
                        }
                        test.finish(TestActivity.this);
                    }
                });
            }
        };
    }

    private void oneTaskTestFun() {
        setHandler();
        test = new Test(id);
        thread.start();
        while (taskAmount == -1) {

        }
        test.setTaskAmount(taskAmount);
        taskArr = new ArrayList<>(taskAmount);
        taskArr.add(0, null);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 1; i <= taskAmount; ++i) {
            taskArr.add(i, new TaskFragment());
            ft.add(R.id.place_holder, taskArr.get(i), String.valueOf(i));
        }
        buttonFragment = new FinishButtonFragment();
        ft.add(R.id.place_holder, buttonFragment);
        ft.commit();

    }

    public void commonTestFun() {
        setHandler();
        thread.start();
        taskAmount = User.getSubject(id).taskAmount;
        taskArr = new ArrayList<>(taskAmount);
        taskArr.add(0, null);
        test = new Test(id);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (byte i = 1; i <= taskAmount; ++i) {
            taskArr.add(i, new TaskFragment());
            ft.add(R.id.place_holder, taskArr.get(i), String.valueOf(i));
        }
        buttonFragment = new FinishButtonFragment();
        ft.add(R.id.place_holder, buttonFragment);
        ft.commit();

    }

    class TestThread extends Thread {
        @Override
        public void run() {
            if (isCommon) {
                common();
            }
            else {
                oneTask();
            }
        }

        private Scanner getStream(String path) {
            URL url;
            Scanner in = null;
            try {
                url = new URL(path);
                in = new Scanner((InputStream) url.getContent());
            } catch (IOException e) {
                e.printStackTrace();
                finish();
                try {
                    this.join();
                } catch (InterruptedException er) {
                    er.printStackTrace();
                }
            }
            return in;
        }

        private int randomTask(byte num) {
            Scanner in = getStream("https:m4xxx1m.github.io/tasks/" + id + "/" + num
                    + "/amount.html");
            int amount = 1;
            try{
                amount = in.nextInt();
                in.close();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                finish();
                try {
                    this.join();
                } catch (InterruptedException er) {
                    er.printStackTrace();
                }
            }
            return (int)(Math.random() * Integer.MAX_VALUE) % amount;
        }

        private int[] getRandomArray(int amount) {
            int[] tasksArray = new int[taskAmount];
            Integer[] objArr = new Integer[amount];
            for (int i = 0; i < amount; ++i) {
                objArr[i] = i;
            }
            List<Integer> list = Arrays.asList(Arrays.stream(objArr).toArray(Integer[]::new));
            Collections.shuffle(list);
            objArr = (Integer[]) list.toArray();
            for (int i = 0; i < taskAmount; ++i) {
                tasksArray[i] = objArr[i];
            }
            return tasksArray;
        }

        private void oneTask() {
            Scanner sc = getStream("https:m4xxx1m.github.io/tasks/" + id + "/" + taskNum
                    + "/amount.html");
            int amount = 0;
            try {
                amount = sc.nextInt();
                taskAmount = Math.min(amount, 10);
                test.setTaskAmount(taskAmount);
                sc.close();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                finish();
                try {
                    this.join();
                } catch (InterruptedException er) {
                    er.printStackTrace();
                }
            }
            int[] tasksArray = getRandomArray(amount);
            for (byte i = 1; i <= taskAmount; ++i) {
                Scanner in = getStream("https://m4xxx1m.github.io/tasks/" + id + "/" +
                        taskNum + "/" + tasksArray[i-1] + ".html");
                boolean hasImage = false;
                String answer = null;
                StringBuilder str = null;
                try {
                    hasImage = Boolean.parseBoolean(in.next());
                    answer = in.next();
                    str = new StringBuilder();
                    while (in.hasNextLine()) {
                        str.append(in.nextLine()).append("\n");
                    }
                    in.close();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    finish();
                    try {
                        this.join();
                    } catch (InterruptedException er) {
                        er.printStackTrace();
                    }
                }
                test.addTask(new Task(id, i, hasImage, /*"",*/ String.valueOf(str), answer));
            }
            handler.sendEmptyMessage(1);
        }

        private void common() {
            for (byte i = 1; i <= taskAmount; ++i) {
                Scanner in = getStream("https://m4xxx1m.github.io/tasks/" + id + "/" +
                            i + "/" + randomTask(i) + ".html");
                boolean hasImage = false;
                String answer = null;
                StringBuilder str = null;
                try {
                    hasImage = Boolean.parseBoolean(in.next());
                    answer = in.next();
                    str = new StringBuilder();
                    while (in.hasNextLine()) {
                        str.append(in.nextLine()).append("\n");
                    }
                    in.close();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    finish();
                    try {
                        this.join();
                    } catch (InterruptedException er) {
                        er.printStackTrace();
                    }
                }
                test.addTask(new Task(id, i, hasImage, /*"",*/ String.valueOf(str), answer));
            }
            handler.sendEmptyMessage(1);
        }
    }
}

