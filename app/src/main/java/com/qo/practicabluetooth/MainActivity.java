package com.qo.practicabluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    TextView mStatusBlueTv, mPairedTv;
    ImageView mBlueIv;
    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn;


    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStatusBlueTv = findViewById(R.id.statusBluetoothTv);
        mPairedTv = findViewById(R.id.mPairedTv);
        mBlueIv = findViewById(R.id.bluetoothIv);
        mOnBtn = findViewById(R.id.onBtn);
        mOffBtn = findViewById(R.id.offBtn);

        //Adaptador bluetooth
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //Chequear si el bluetooth esta disponible
        if(mBluetoothAdapter == null){
            mStatusBlueTv.setText("El bluetooth no esta disponible");
        }
        else{
            mStatusBlueTv.setText("El bluetooh está disponible");
        }

        //Poner imagen acorde al estado del bluetooth (on/off)
        if(mBluetoothAdapter.isEnabled()){
            mBlueIv.setImageResource(R.drawable.ic_action);
        }
        else{
            mBlueIv.setImageResource(R.drawable.ic_action_off);
        }

        //On btn click
        mOnBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!mBluetoothAdapter.isEnabled()){
                    showToast("Encendiendo bluetooth");

                    //Intent para encendder bluetooh
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                }
                else{
                    showToast("El bluetooth ya está activado");
                }
            }
        });

        //Boton para hacer el dispositivos visible
        mDiscoverBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        //Boton off
            mOffBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        //Boton de obtener dispositivos vinculados
        mPairedBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if(resultCode == RESULT_OK){
                    //El bluetooth está activado
                    mBlueIv.setImageResource(R.drawable.ic_action);
                    showToast("El bluetooth está activado");
                }
                else{
                    //El ususario no ha dado el permiso
                    showToast("No se pudo activar el bluetooth");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Funcion para mensaje flotante ahorrando codigo
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
