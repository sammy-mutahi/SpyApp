package sammy.mutahi.gicheru.childSpyApp.ui.dashboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_sales.view.*
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.ui.dashboard.models.UserObjec

class UserAdapter(var userlist:ArrayList<UserObjec>):RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.card_sales,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = userlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userlist[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(user:UserObjec){
            with(user){
                itemView.tv_name.text = name
                itemView.tv_date_time.text = date_time
            }
        }
    }
}