package ro.razvan.ipercalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setTitle(R.string.result)

        val operators = intent.getCharArrayExtra(getString(R.string.operators))
        val operands = intent.getFloatArrayExtra(getString(R.string.operands))

        var result = 0f

        for (i in 0 until operands.size)
            if (i > 0)
                result = interpreter(result, operands[i], operators[i - 1])
            else
                result = interpreter(result, operands[i], '+')

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txvResult.text = result.toString()
    }

    private fun interpreter(o1: Float, o2: Float, char: Char) = when (char) {
        '+' -> o1 + o2
        '-' -> o1 - o2
        '*' -> o1 * o2
        '/' -> o1 / o2
        else -> 0f
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
