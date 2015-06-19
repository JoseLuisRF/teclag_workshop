package jlrf.itl.gsa.listviewexample;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jlrf.itl.gsa.listviewexample.db.MyContactsCmd;
import jlrf.itl.gsa.listviewexample.model.MyContact;

/**
 * Created by joseluisrf on 6/13/15.
 */
public class RegisterDialog extends Dialog {

    EditText etName, etMail, etAge;
    Button btnSave;


    Context context;


    public RegisterDialog(final Context context) {
        super(context);
        this.context = context;

        setTitle("Register Contact");
        setContentView(R.layout.register_contact_dialog);
        etName = (EditText)this.findViewById(R.id.etName);
        etMail = (EditText)this.findViewById(R.id.etMail);
        etAge = (EditText)this.findViewById(R.id.etAge);
        btnSave = (Button)this.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validating
                if(etName.getText().toString().equals("")){
                    Toast.makeText(context,
                            "Name cannot be left blank",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etMail.getText().toString().equals("")){
                    Toast.makeText(context,
                            "Mail cannot be left blank",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etAge.getText().toString().equals("")){
                    Toast.makeText(context,
                            "Age cannot be left blank",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                MyContactsCmd cmd = new MyContactsCmd(context);
                MyContact contact = new MyContact();
                contact.setMail(etMail.getText().toString());
                contact.setName(etName.getText().toString());
                contact.setAge(Integer.parseInt(etAge.getText().toString()));
                try {
                    long r = cmd.insert(contact);
                    if(r > 0) {
                        Toast.makeText(context,
                                "Success Insert",
                                Toast.LENGTH_SHORT).show();

                        dismiss();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context,
                            "Exception:" + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
