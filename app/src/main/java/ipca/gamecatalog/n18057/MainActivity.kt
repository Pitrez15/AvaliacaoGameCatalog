package ipca.gamecatalog.n18057

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.app.ActivityCompat
import ipca.gamecatalog.n18057.models.Game
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    var games : MutableList<Game> = ArrayList<Game>()
    var gamesAdapter : GamesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gamesAdapter = GamesAdapter()
        listViewGames.adapter = gamesAdapter

        buttonAdd.setOnClickListener {

            val intent = Intent(this, CreateGameActivity::class.java)
            startActivityForResult(intent, 1002)
        }
    }

    inner class GamesAdapter : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var rowView = layoutInflater.inflate(R.layout.games_row, parent, false)

            val textViewGameName = rowView.findViewById<TextView>(R.id.textViewRowGameName)
            val textViewCompanyName = rowView.findViewById<TextView>(R.id.textViewRowCompanyName)
            val textViewScore = rowView.findViewById<TextView>(R.id.textViewRowScore)

            textViewGameName.text = games[position].gameName
            textViewCompanyName.text = games[position].companyName
            textViewScore.text = games[position].score

            rowView.setOnClickListener {

                val intent = Intent(this@MainActivity, GameDetailActivity::class.java)
                intent.putExtra("Game Name", games[position].gameName)
                intent.putExtra("Company Name", games[position].companyName)
                intent.putExtra("Score", games[position].score)
                startActivity(intent)
            }

            return rowView
        }

        override fun getItem(position: Int): Any {
            return games[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return games.size
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode === Activity.RESULT_OK) {

            if (requestCode == 1002) {

                data?.extras?.let {

                    val nameGame : String? = it.getString(CreateGameActivity.GAME_NAME)
                    val nameCompany = it.getString(CreateGameActivity.COMPANY_NAME)
                    val scoreGame = it.getString(CreateGameActivity.SCORE)
                    val game = Game()

                    if (nameGame != null) {
                        game.gameName = nameGame
                    }
                    game.companyName = nameCompany
                    game.score = scoreGame

                    games.add(game)

                    gamesAdapter?.notifyDataSetChanged()
                }
            }
        }
    }
}
