package ipca.gamecatalog.n18057

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_game_detail.*

private var GameName : String? = null
private var CompanyName : String? = null
private var Score : String? = null

class GameDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        val bundle = intent.extras
        bundle?.let {

            GameName = it.getString("Game Name")
            CompanyName = it.getString("Company Name")
            Score = it.getString("Score")
        }

        textViewNameDetail.text = GameName.toString()
        textViewCompanyDetail.text = CompanyName.toString()
        textViewScoreDetail.text = Score.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.share){
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, GameName)
            i.putExtra(Intent.EXTRA_TEXT, CompanyName)
            i.putExtra(Intent.EXTRA_TEXT, Score)
            startActivity(Intent.createChooser(i, "Game Detail"))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
