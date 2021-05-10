package com.example.dangerouspermissions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var editText:EditText
    lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText= findViewById(R.id.editTextPhone2)
        btn= findViewById(R.id.button)

        btn.setOnClickListener {
            //check if we have permission
            val perm:Int = ContextCompat.checkSelfPermission(this@MainActivity,
                                                Manifest.permission.CALL_PHONE)
            if(perm == PackageManager.PERMISSION_GRANTED){
                //permission is already granted
                callNumber()
            }else{
                //request for permission
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    //String array of permissions required
                        arrayOf(Manifest.permission.CALL_PHONE),
                    //Request code :Any arbitary integer
                        121
                )
            }
        }


    }
    private fun callNumber(){
        var i = Intent()
        val tel: String = editText.text.toString()
        //i.action = Intent.ACTION_VIEW
        //ACTION_CALL is a dangerous permission and requires permission by user at runtime.
        //Adding permission to manifest does not works in case of dangerous permissions.
        i.action = Intent.ACTION_CALL
        i.data = Uri.parse("tel:$tel")
        startActivity(i)
    }
}