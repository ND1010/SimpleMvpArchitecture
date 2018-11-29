package ndtech.app.com.simplemvparchitecture.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.regex.Pattern;

import ndtech.app.com.simplemvparchitecture.R;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by techno on 29/11/18.
 */

public class Utility {
    private static final String TAG = Utility.class.getSimpleName();

    private static Dialog progressDialog = null;
    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\._%\\-\\+]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    public static void hideKeyboard(Activity aContext) {
        if (aContext != null) {
            InputMethodManager im = (InputMethodManager) aContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(aContext.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static RequestBody getRequest(String reqString) {
        MediaType mediaType = MediaType.parse(Constants.ApiHeaders.API_TYPE_JSON);
        return RequestBody.create(mediaType, reqString);
    }


    public static void showSnackBar(View view, String message, int time, boolean isTypeError, final Context context) {

        final Snackbar snackbar = Snackbar.make(view, message, time);
        View snackBarView = snackbar.getView();
        TextView snackTextView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        snackTextView.setMaxLines(4);
        if (isTypeError) {
            snackBarView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        } else {
            snackBarView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
        }
        snackbar.show();
    }


    public static boolean isValidEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static boolean isGpsOn(Context mContext) {
        LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        } else
            return true;
    }


    /**
     * This method is used to show progress indicator dialog with message when
     * web service is called.
     */
    public static void showProgressDialog(Context context, String message) {

        if (context != null && !((Activity) context).isFinishing()) {
            if (progressDialog == null || !progressDialog.isShowing()) {
                progressDialog = new Dialog(context);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                try {
                    int dividerId = progressDialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
                    View divider = progressDialog.findViewById(dividerId);
                    if (divider != null)
                        divider.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    TextView mTitle = (TextView) progressDialog.findViewById(android.R.id.title);
                    if (mTitle != null)
                        mTitle.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                    int x = Resources.getSystem().getIdentifier("titleDivider", "id", "android");
                    View titleDivider = progressDialog.findViewById(x);
                    if (titleDivider != null)
                        titleDivider.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.setContentView(R.layout.custom_progressbar);
                TextView tvMessage = (TextView) progressDialog.findViewById(R.id.txtMessage);
                if (!message.equals("")) {
                    tvMessage.setText(message);
                }
                progressDialog.setCancelable(false);
                if (!((Activity) context).isFinishing())
                    progressDialog.show();
            }
        }
    }

    /**
     * Method Name: hideProgressDialog Created By: dev458 Created Date:
     * 28/March/2013 Modified By: Modified Date: Purpose: This method is used to
     * hide progress dialog and destroy progress dialog instance .
     */
    public static void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Throwable throwable) {

        } finally {
            progressDialog = null;
        }
    }



}
