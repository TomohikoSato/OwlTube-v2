package com.sgr.owltube_v2.frontend.common

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import com.sgr.owltube_v2.common.ext.Logger

/**
 * In the case of BottomNavigationView has above 4 items,
 * the active tab width becomes too large. The cause is shiftingMode flag.
 * We can't access the flag and can't override the class. Then I hacked like this :(
 * http://stackoverflow.com/questions/40176244/how-to-disable-bottomnavigationview-shift-mode
 * @since support library 26.0.2
 */
@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftingMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0..menuView.childCount - 1) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            // Set once again checked level, so view will be updated
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Logger.e("Unable to get shift mode field")
    } catch (e: IllegalAccessException) {
        Logger.e("Unable to change level of shift mode")
    }
}
