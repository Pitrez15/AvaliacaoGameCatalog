package ipca.gamecatalog.n18057

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ipca.gamecatalog.n18057.models.Game
import kotlinx.android.synthetic.main.activity_create_game.*
import kotlinx.android.synthetic.main.games_row.*


class CreateGameActivity : AppCompatActivity() {

    private var gameName : String? = null
    private var companyName : String? = null
    private var score : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        buttonAdd.setOnClickListener {

            val intentResult = Intent()

            intentResult.putExtra(GAME_NAME, editText1.text.toString())
            intentResult.putExtra(COMPANY_NAME, editText2.text.toString())
            intentResult.putExtra(SCORE, editText3.text.toString())

            setResult(Activity.RESULT_OK, intentResult)
            finish()
        }
    }

    companion object {

        val GAME_NAME = "Game Name"
        val COMPANY_NAME = "Company Name"
        val SCORE = "Score"

        const val REQUEST_CODE_GAME = 1003
    }
}