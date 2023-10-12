package kr.ac.kumoh.s20191283.s23w03carddealer1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.s20191283.s23w03carddealer1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    private lateinit var model: CardDealerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        model = ViewModelProvider(this)[CardDealerViewModel::class.java]
        model.cards.observe(this, Observer {
            val res = IntArray(5)
            for(i in res.indices) {
            res[i] = resources.getIdentifier(getCardName(it[0]), "drawble", packageName)
            }
            main.imgCard01.setImageResource(res[0])

    })

        val res = resources.getIdentifier(getCardName(37), "drawble", packageName)
        main.imgCard01.setImageResource(res)
        }

        private fun getCardName(c: Int): String {
            val shape = when (c / 13) {
                0 -> "spades"
                1 -> "diamonds"
                2 -> "hearts"
                3 -> "clubs"
                else -> "error"
            }
            val number = when (c % 13) {
                0 -> "ace"
                in 1..9 -> (c % 13 + 1).toString()
                10 -> "jack"
                11 -> "queen"
                12 -> "king"
                else -> "error"
            }
            return if (number in arrayOf("jack", "queen", "king"))
                "c_${number}_of_${shape}2"
            else
                "c_${number}_of_${shape}"
        }
}
