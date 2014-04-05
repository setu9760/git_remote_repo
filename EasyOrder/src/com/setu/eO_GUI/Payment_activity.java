package com.setu.eO_GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.setu.EasyOrder.R;
import com.setu.eO_Logic.*;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Payment_activity extends Activity implements OnClickListener {
	private TextView lblorder;
	private EditText txtfname;
	private EditText txtsurname;
	private EditText txthouseno;
	private EditText txtpostcode;
	private EditText txtcreditcard;
	private EditText txtemail;
	private Button btnsubmit;
	private final List<Order> finalorder = Second_activity.orderlist;
	private String fname, surname, houseno, postcode, email;
	private Double total, subtotal, taxes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_screen);

		Bundle bundle = getIntent().getExtras();
		subtotal = bundle.getDouble("key_subtotal");
		taxes = bundle.getDouble("key_taxes");
		total = bundle.getDouble("key_total");

		lblorder = (TextView) findViewById(R.id.lblorder);
		txtfname = (EditText) findViewById(R.id.txtfname);
		txtsurname = (EditText) findViewById(R.id.txtsurname);
		txthouseno = (EditText) findViewById(R.id.txthouseno);
		txtpostcode = (EditText) findViewById(R.id.txtpostcode);
		txtcreditcard = (EditText) findViewById(R.id.txtcreditcard);
		txtemail = (EditText) findViewById(R.id.txtemail);
		btnsubmit = (Button) findViewById(R.id.btnsubmit);
		lblorder.setText("Total payment : £" + total);
		btnsubmit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnsubmit:
			if (!(isEmpty(txtfname)) && !(isEmpty(txtsurname))
					&& !(isEmpty(txtpostcode)) && !(isEmpty(txtpostcode))
					&& !(isEmpty(txtcreditcard)) && !(isEmpty(txtcreditcard))) {
				if (submitorder()) {
					/*
					 * start success activity and set flags to clear history
					 */
				}

			} else {
				Toast.makeText(this,
						"All field are compulsory \nPlease enter all data",
						Toast.LENGTH_LONG).show();
			}
			break;
		}

	}

	public boolean submitorder() {

		fname = txtfname.getText().toString();
		surname = txtsurname.getText().toString();
		houseno = txthouseno.getText().toString();
		postcode = txtpostcode.getText().toString();
		email = txtemail.getText().toString();

		File externalpath = Environment.getExternalStorageDirectory();
		File orderfile = new File(externalpath, "order.txt");

		try {
			FileWriter filewriter = new FileWriter(orderfile);
			BufferedWriter bfw = new BufferedWriter(filewriter);

			bfw.write(fname + " " + surname);
			bfw.newLine();
			bfw.write(houseno + "  " + postcode);
			bfw.newLine();
			bfw.write(email);
			bfw.newLine();
			for (Order order : finalorder) {
				bfw.write(order.getProduct() + " : £" + order.getPrice());
				bfw.newLine();
			}
			bfw.write("------------------------");
			bfw.newLine();
			bfw.write("Subtotal : £" + this.subtotal);
			bfw.newLine();
			bfw.write("Taxes : £" + this.taxes);
			bfw.newLine();
			bfw.write("Total : £" + this.total);
			bfw.close();
			return true;
		} catch (IOException e) {
			Log.e("Setu", "IOException " + e);
			e.printStackTrace();
			return false;
		}

	}

	public boolean isEmpty(EditText text) {
		if (text.getText().toString().trim().length() != 0)
			return false;
		else
			return true;
	}
}
