package ro.razvan.ipercalculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val operators = ArrayList<Char>()
    private val operands = ArrayList<Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "arraySize: ${operators.size} ${operands.size}")
        Log.d(TAG, "CREATING")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("txvActualNumber", txvActualNumber.text.toString())
        outState?.putString("txvOperations", txvOperations.text.toString())
        outState?.putCharArray(getString(R.string.operators), operators.toCharArray())
        outState?.putFloatArray(getString(R.string.operands), operands.toFloatArray())

        Log.d(TAG, "arraySize: ${operators.size} ${operands.size}")
        Log.d(TAG, "SAVING")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        txvActualNumber.text = savedInstanceState?.getString("txvActualNumber")
        txvOperations.text = savedInstanceState?.getString("txvOperations")


        val arr = savedInstanceState?.getCharArray(getString(R.string.operators))
        if (arr != null) {
            for (i in 0 until arr.size)
                operators.add(arr[i])
        }

        val arr2 = savedInstanceState?.getFloatArray(getString(R.string.operands))
        if (arr2 != null) {
            for (i in 0 until arr2.size)
                operands.add(arr2[i])
        }

        Log.d(TAG, "arraySize: ${operators.size} ${operands.size}")
        Log.d(TAG, "RECREATING")
    }


    fun getResult(view: View) {
        val int = Intent(this, ResultActivity::class.java)

        operands.add(txvActualNumber.text.toString().toFloat())

        int.putExtra(getString(R.string.operands), operands.toFloatArray())
        int.putExtra(getString(R.string.operators), operators.toCharArray())

        startActivity(int)
    }

    fun clear(view: View) {
        operStatus(true)
        txvOperations.text = ""
        txvActualNumber.text = "0"
        btnEquals.isEnabled = false
        operators.clear()
        operands.clear()
    }

    fun clickKeyboard(view: View) {

        val textButton = (view as Button).text.toString()
        val actualNumber = txvActualNumber.text.toString()

        try {
            if (actualNumber.toInt() == 0) {
                txvActualNumber.text = textButton
                operStatus(true)
            } else
                txvActualNumber.text = actualNumber + textButton.toInt()

        } catch (exc: NumberFormatException) {
            txvOperations.text = txvOperations.text.toString() + actualNumber + textButton
            operators.add(textButton[0])
            operands.add(actualNumber.toFloat())
            operStatus(false)

            btnEquals.isEnabled = true
            txvActualNumber.text = "0"
        }
    }

    private fun operStatus(boolean: Boolean) {
        btnPlus.isEnabled = boolean
        btnMinus.isEnabled = boolean
        btnFor.isEnabled = boolean
        btnDivided.isEnabled = boolean
    }

}
