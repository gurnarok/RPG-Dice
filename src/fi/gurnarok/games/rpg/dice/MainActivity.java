package fi.gurnarok.games.rpg.dice;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	public int max = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll_dice();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void roll_dice()
    {
    	final Random r = new Random();
    	final TextView roll_number = (TextView)findViewById(R.id.roll_numbers);
        final TextView roll_result = (TextView)findViewById(R.id.roll_results);
        
        Spinner dices = (Spinner) findViewById(R.id.dice_set);
    	dices.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				switch (pos)
				{
					case 0: max = 4; break;
					case 1: max = 6; break;
					case 2: max = 8; break;
					case 3: max = 10; break;
					case 4: max = 12; break;
					case 5: max = 20; break;
					case 6: max = 100; break;
					default: max = 4; break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0)
			{
				max = 4;
			}
    		
		});
        
    	final Button roll_btn = (Button) findViewById(R.id.roll_btn);
        roll_btn.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v)
			{
				//int rolls = Integer.parseInt((String) roll_number.getText());
				//int res = 0;
				int rolls = Integer.parseInt(roll_number.getText().toString());
				int res = 0;
				
				if(rolls>1)
				{
					res = 0;
					for(int a=0;a<rolls;a++)
					{
						int dr = r.nextInt(max-1)+1;
						res = res+dr;
						//System.out.println("Roll "+a+"\nRolled: "+dr+"\nRoll total: "+res);
					}
				}
				else
				{
					res = r.nextInt(max-1)+1;
				}
				
				String results = roll_result.getText().toString();
				String msg = "";
				if(rolls>1)
				{
					msg = "You rolled "+rolls+" "+max+" sided dices, the sum was "+res;
				}
				else
				{
					msg = "You rolled a "+max+" sided dice, result is "+res;
				}
				
				results = msg+"\n"+results;
				roll_result.setText(results);				
			}
		});
    }
}
