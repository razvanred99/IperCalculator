package ro.razvan.ipercalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val operators=ArrayList<Char>()
    private val operands=ArrayList<Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getResult(view: View){
        val int= Intent(this,ResultActivity::class.java)
        int.putExtra(getString(R.string.operands),operands.toArray())
        int.putExtra(getString(R.string.operators),operators.toArray())
    }

    fun clear(view:View){
        operStatus(true)
        txvOperations.text=""
        operators.clear()
        operands.clear()
    }

    fun clickKeyboard(view:View){
        val text=(view as TextView).text.toString()
        val actualNumber=txvActualNumber.text.toString()

        try{
            if(txvActualNumber.text.toString().toInt()==0)
                txvActualNumber.text = text
            else
                txvActualNumber.text=actualNumber+text

        }catch(exc:NumberFormatException){
            txvOperations.text=actualNumber+text
            operators.add(text[0])
            operands.add(actualNumber.toFloat())
            operStatus(false)
        }
    }

    private fun operStatus(boolean: Boolean){
        btnPlus.isEnabled=boolean
        btnMinus.isEnabled=boolean
        btnFor.isEnabled=boolean
        btnDivided.isEnabled=boolean

        btnEquals.isEnabled=!boolean
        txvActualNumber.text="0"
    }
}
