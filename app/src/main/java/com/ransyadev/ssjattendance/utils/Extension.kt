package com.ransyadev.ssjattendance.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ransyadev.ssjattendance.R

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> Fragment.openActivity(destination: Class<T>, bundleKey: String, id: String) {
    val intent = Intent(requireContext(), destination)
    intent.putExtra(bundleKey, id)
    startActivity(intent)
}

fun <T> Fragment.openActivity(destination: Class<T>) {
    val intent = Intent(requireContext(), destination)
    startActivity(intent)
}

fun <T> Activity.openActivity(destination: Class<T>, bundleKey: String, id: String) {
    val intent = Intent(this, destination)
    intent.putExtra(bundleKey, id)
    startActivity(intent)
}

fun <T> Activity.openActivity(destination: Class<T>) {
    val intent = Intent(this, destination)
    startActivity(intent)
}

fun Activity.closeActivity(){
    hideKeyboard()
    finish()
}

fun View.visible() {
    if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) this.visibility =
        View.VISIBLE
}

fun View.gone() {
    if (this.visibility == View.VISIBLE) this.visibility = View.GONE
}

fun View.invisible() {
    if (this.visibility == View.VISIBLE) this.visibility = View.INVISIBLE
}

fun Activity.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus
    if (view != null) {
        inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun convertDpToPixel(dp: Float, context: Context): Float {
    return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun AppCompatActivity.callPhone(data : String) {
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse("tel: ${data}")
    startActivity(intent)
}

fun Fragment.callPhone(data : String) {
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse("tel: ${data}")
    startActivity(intent)
}

fun Context.callPhone(data : String) {
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse("tel: ${data}")
    startActivity(intent)
}

fun Boolean.doThis(job: () -> Unit) {
    if (this)
        job()
}

fun View.showSnackBarInfo(
    message: String?,
    duration: Int = Snackbar.LENGTH_LONG,
) {
    val snackBar = Snackbar.make(this, message ?: "", duration)
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.alert_info_background))
        .setTextColor(ContextCompat.getColor(this.context, R.color.alert_info_text))
    val snackBarView = snackBar.view

    val textView =
        snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

    textView.maxLines = 4

    snackBar.show()
}

fun View.showSnackBarError(
    message: String?,
    duration: Int = Snackbar.LENGTH_LONG,
) {
    val snackBar = Snackbar.make(this, message ?: "", duration)
        .setBackgroundTint(
            ContextCompat.getColor(
                this.context,
                R.color.alert_error_background
            )
        )
        .setTextColor(
            ContextCompat.getColor(
                this.context,
                R.color.alert_error_text
            )
        )
    val snackBarView = snackBar.view

    val textView =
        snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

    textView.maxLines = 4

    snackBar.show()
}
