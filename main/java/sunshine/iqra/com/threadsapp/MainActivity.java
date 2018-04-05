package sunshine.iqra.com.threadsapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvCounter = (TextView) findViewById(R.id.counter);

        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message message) {
                Bundle bundle = message.getData();

                tvCounter.setText(String.valueOf(bundle.getInt("counter")));
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<Integer.MAX_VALUE; i++) {
                    //Log.d("Thread", "Counter :: " + String.valueOf(i));

                    Bundle bundle = new Bundle();
                    bundle.putInt("counter", i);
                    Message message = new Message();
                    message.setData(bundle);

                    handler.sendMessage(message);

                    //if(i%25 == 0) {
                        try {
                            Thread.sleep(1000);
                        }
                        catch(InterruptedException iox){
                            iox.printStackTrace();
                        }
                    //}
                }

            }
        });

        thread.start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
