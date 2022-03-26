package com.frankcinatra.coupons

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.frankcinatra.coupons.mainModule.view.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityCreateTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createCouponTest(){
        val etCoupon = Espresso.onView(withId(R.id.etCoupon))
        etCoupon.check(ViewAssertions.matches(ViewMatchers.withText("")))//verifica que(view)(concida(con el texto("")))
        etCoupon.perform(ViewActions.click())
        etCoupon.perform(ViewActions.replaceText("WELCOME_02"))

        val btnConsult = Espresso.onView(withId(R.id.btnConsult))
        btnConsult.perform(ViewActions.click())

        val btnCreate = Espresso.onView(withId(R.id.btnCreate))
        btnCreate.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val etDescription = Espresso.onView(withId(R.id.etDescription))
        etDescription.perform(ViewActions.replaceText("2x1 en sodas."))

        btnCreate.perform(ViewActions.click())

        val snackbar =
            Espresso.onView(ViewMatchers.withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(ViewAssertions.matches(ViewMatchers.withText("Cupón creado.")))
    }

    /*
    * Corrobora que el botón "crear" no existe y no es visible.
    * Test: nuestro etCoupon inicia vacío, luego haz click sobre el, añade el texto "WELCOME_01"
    * y ahora desde btnConsult, haz click sobre el.
    * Verifica que el btnCrear no es visible.
    * */
    @Test
    fun consultCouponExistTest(){
        val etCoupon = Espresso.onView(withId(R.id.etCoupon))
        etCoupon.check(ViewAssertions.matches(ViewMatchers.withText("")))
        etCoupon.perform(ViewActions.click())
        etCoupon.perform(ViewActions.replaceText("WELCOME_01"))

        val btnConsult = Espresso.onView(withId(R.id.btnConsult))
        btnConsult.perform(ViewActions.click())

        val btnCreate = Espresso.onView(withId(R.id.btnCreate))
        btnCreate.check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))// ! = not()
    }

    /*
    * Comprueba que no se puede crear un cupón con código repetido.
    * Test: etCoupon inicia vacío, y se replaza texto con "WELCOME_01A(cupón existente)".
    * Click en btnConsult.
    * Corrobora que btnCreate existe.
    * Añade descripción y edita etCoupon(por un código existente, ej: WELCOME_01)
    * Click en btnCreate.
    * Comprueba el mensaje de snackbar(Este cupón ya existe, agregue un código diferente.).
    * */
    @Test
    fun createCouponWithOldCodeTest(){
        val etCoupon = Espresso.onView(withId(R.id.etCoupon))
        etCoupon.perform(ViewActions.replaceText("WELCOME_01A"))

        val btnConsult = Espresso.onView(withId(R.id.btnConsult))
        btnConsult.perform(ViewActions.click())

        val btnCreate = Espresso.onView(withId(R.id.btnCreate))
        btnCreate.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val etDescription = Espresso.onView(withId(R.id.etDescription))
        etDescription.perform(ViewActions.replaceText("3x2 en chocolates."))
        etCoupon.perform(ViewActions.replaceText("WELCOME_01"))

        btnCreate.perform(ViewActions.click())

        val snackbar =
            Espresso.onView(ViewMatchers.withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(ViewAssertions.matches(ViewMatchers.withText("Este cupón ya existe, agregue un código diferente.")))
    }
}