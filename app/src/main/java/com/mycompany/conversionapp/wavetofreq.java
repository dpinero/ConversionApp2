package com.mycompany.conversionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class wavetofreq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wavetofreq);

        Spinner dropdownwave = (Spinner)findViewById(R.id.spinner3);
        String[] waveitems = new String[]{"Meter", "Millimeter", "Micrometer", "Nanometer","Angstrom"};
        ArrayAdapter<String> waveadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, waveitems);
        dropdownwave.setAdapter(waveadapter);

        Spinner dropdownfreq = (Spinner)findViewById(R.id.spinner4);
        String[] freqitems = new String[]{"Hz", "kHz", "MHz", "GHz", "THz"};
        ArrayAdapter<String> freqadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, freqitems);
        dropdownfreq.setAdapter(freqadapter);

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
    public void WavetoFreq(View view) {
        Double testdouble;
        Double c=299792458.0; //speed of light in m/s
        Double[] freqarray=new Double[]{1.0,1000.0,1000000.0,1000000000.0,1000000000000.0};
        Double[] wavearray=new Double[]{1.0,0.001,0.000001,0.000000001,0.0000000001};
        // We create and EditText object to get the text input into the EditText box from the layout activity_my.xml
        EditText editText = (EditText) findViewById(R.id.edit_message2);
        //  check if first text box is empty
        if(TextUtils.isEmpty(editText.getText())) {
            testdouble = 0.0;
        }else {
            // We then turn that EditText object into a string or double
            testdouble = Double.parseDouble(editText.getText().toString());
        }
        // get spinner locations
        Spinner freqdropdown = (Spinner)findViewById(R.id.spinner4);
        Integer freqposition=freqdropdown.getSelectedItemPosition();
        Spinner wavedropdown = (Spinner)findViewById(R.id.spinner3);
        Integer waveposition=wavedropdown.getSelectedItemPosition();
        // get conversion factors based on spinner locations
        Double freqfactor = freqarray[freqposition];
        Double wavefactor = wavearray[waveposition];
        Double convert = 1/(testdouble*freqfactor*wavefactor*(1/c));
        // String converttext = convert.toString();
        TextView textView = (TextView) findViewById(R.id.display_message2);
        textView.setTextSize(30);
        textView.setText(String.format("%.5f", convert));
    }

}
