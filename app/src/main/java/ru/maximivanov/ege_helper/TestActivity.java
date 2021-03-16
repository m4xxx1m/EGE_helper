package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class TestActivity extends AppCompatActivity {
    private Test test;
    private byte id;
    private boolean isCommon;
    private int taskAmount;
    Handler handler;
    ArrayList<TaskFragment> taskArr;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        id = getIntent().getByteExtra("subject", (byte) 0);
        isCommon = getIntent().getBooleanExtra("isCommon", true);
        if (isCommon) {
            CommonTestThread thread = new CommonTestThread();
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
            FinishButtonFragment buttonFragment = new FinishButtonFragment();
            ft.add(R.id.place_holder, buttonFragment);
            ft.commit();

            handler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    RelativeLayout rl = findViewById(R.id.test_layout);
                    rl.removeView(findViewById(R.id.loading));
                    test.set(getSupportFragmentManager());
                    buttonFragment.getButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 1; i <= taskAmount; ++i) {
                                String userAnswer = taskArr.get(i).getUserAnswer();
                                String correctAnswer = test.getTasks().get(i-1).getAnswer();
                                if (userAnswer.equals(correctAnswer)) {
                                    test.incrementTestScore();
                                    SubjectsList.getSubject(id).tasksAnswersScore[i]++;
                                }
                                else {
                                    int oldJ = -1, thisJ = 0;
                                    boolean isRight = false;
                                    for (int j = 0; j < correctAnswer.length(); ++j) {
                                        if (correctAnswer.charAt(j) == '|') {
                                            if (userAnswer.equals(correctAnswer
                                                    .substring(oldJ+1, j))) {
                                                test.incrementTestScore();
                                                SubjectsList.getSubject(id).tasksAnswersScore[i]++;
                                                isRight = true;
                                                break;
                                            }
                                            oldJ = thisJ;
                                            thisJ = j;
                                        }
                                    }
                                    if (!isRight) {
                                        SubjectsList.getSubject(id).tasksAnswersScore[i-1]--;
                                    }
                                }
                            }
                            test.finish();
                        }
                    });
                }
            };
        }
    }

    class CommonTestThread extends Thread {
        @Override
        public void run() {
            for (byte i = 1; i <= taskAmount; ++i) {
                URL url = null;
                try {
                    url = new URL("https://m4xxx1m.github.io/tasks/" + id + "/" + i + "/"
                            + 0 + ".html");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Scanner in = null;
                try {
                    assert url != null;
                    in = new Scanner((InputStream) url.getContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert in != null;
                boolean hasImage = Boolean.parseBoolean(in.next());
                String answer = in.next();
                StringBuilder str = new StringBuilder();
                while (in.hasNextLine()) {
                    str.append(in.nextLine()).append("\n");
                }
                test.addTask(new Task(id, i, hasImage, "", String.valueOf(str), answer));
            }
            handler.sendEmptyMessage(1);
        }
    }
}

