package com.burhanrashid52.photoediting.tools

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.jaredrummler.android.colorpicker.ColorShape


fun FragmentActivity.createColorPickerDialog(
    fragmentManager: FragmentManager,
    color: Int,
    onColorSelected: ((Int, Int) -> Unit)? = null,
    onDialogDismissed: ((Int) -> Unit)? = null
) {

    val mColorPickerDialog: ColorPickerDialog = ColorPickerDialog
        .newBuilder()
        .setColor(color)
        .setShowAlphaSlider(true)
        .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
        .setColorShape(ColorShape.SQUARE)
        .setShowColorShades(true)
        .setAllowPresets(false)
        .create()
    mColorPickerDialog.setColorPickerDialogListener(object : ColorPickerDialogListener {
        override fun onColorSelected(dialogId: Int, color: Int) {
            onColorSelected?.invoke(dialogId, color)
        }

        override fun onDialogDismissed(dialogId: Int) {
            onDialogDismissed?.invoke(dialogId)
        }
    })
    mColorPickerDialog.show(fragmentManager, "color-picker-dialog")
}

fun FragmentActivity.createColorPickerDialog(
    color: Int,
    onColorSelected: ((Int, Int) -> Unit)? = null,
    onDialogDismissed: ((Int) -> Unit)? = null
) {
    createColorPickerDialog(supportFragmentManager, color, onColorSelected, onDialogDismissed)
}

fun Fragment.createColorPickerDialog(
    color: Int,
    onColorSelected: ((Int, Int) -> Unit)? = null,
    onDialogDismissed: ((Int) -> Unit)? = null
) {
    activity?.createColorPickerDialog(
        childFragmentManager,
        color,
        onColorSelected,
        onDialogDismissed
    )
}