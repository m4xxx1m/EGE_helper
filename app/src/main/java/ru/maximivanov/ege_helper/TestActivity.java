package ru.maximivanov.ege_helper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class TestActivity extends AppCompatActivity {
    private Test test;
    private byte id;
    private boolean isCommon;
    private int taskAmount;
    private final Activity act = this;
    Handler handler;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        id = getIntent().getByteExtra("subject", (byte) 0);
        isCommon = getIntent().getBooleanExtra("isCommon", true);
        if (isCommon) {
            taskAmount = User.getSubject(id).taskAmount;
            test = new Test(id);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            for (byte i = 1; i <= taskAmount; ++i) {
                TaskFragment fragment = new TaskFragment();
                ft.add(R.id.place_holder, fragment, String.valueOf(i));
            }
            ft.commit();
            CommonTestThread thread = new CommonTestThread();
            thread.start();
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                }
            };
//            while (thread.isAlive()) {
//            }
//            test.set(getSupportFragmentManager());
        }
    }

    class CommonTestThread extends Thread {
        @Override
        public void run() {

//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            synchronized (act) {
                for (byte i = 1; i <= taskAmount; ++i) {
//                TaskFragment fragment = new TaskFragment();
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
//                ft.add(R.id.place_holder, fragment);
//                ft.commit();

//                TaskFragment fragment = (TaskFragment) getSupportFragmentManager()
//                        .findFragmentByTag(String.valueOf(i));
//                assert fragment != null;
//                fragment.set(i, i, User.getSubject(id).name, String.valueOf(str));
                }
                //notifyAll();
            }
        }
    }
}

