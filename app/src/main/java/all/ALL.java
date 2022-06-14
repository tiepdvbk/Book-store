package all;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.bookstoreapp.R;

public class ALL {
    public static double allPrice;
    public static int id_donhang;
    public static void showDialog(String s, Context ct){
        AlertDialog.Builder builder = new AlertDialog.Builder(ct);
        builder.setTitle(s);
        builder.setIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }
}
