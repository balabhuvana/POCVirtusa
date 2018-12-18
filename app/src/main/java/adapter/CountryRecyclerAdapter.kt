package adapter

/**
 * Created by bala on 4/12/18.
 */
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.virtusa.com.poc_virtusa.R
import model.CountryData


class CountryRecyclerAdapter(private var mCtx: Context, private var notesList: ArrayList<CountryData>) : RecyclerView.Adapter<CountryRecyclerAdapter.SampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.user_row, parent, false)
        return SampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val country = notesList[position]

        holder.tvTitle!!.text = country.title
        holder.tvContent!!.text = country.description
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView? = null
        var tvContent: TextView? = null

        init {

            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvContent = itemView.findViewById(R.id.tvContent)
        }
    }

    // Clean all elements of the recycler
    fun clear() {
        notesList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: ArrayList<CountryData>) {
        notesList.addAll(list)
        notifyDataSetChanged()
    }
}