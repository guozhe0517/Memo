package com.guozhe.android.memo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guozhe.android.memo.domain.Memo;

import java.util.ArrayList;

/**
 * Created by guozhe on 2017. 5. 30..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    ArrayList<Memo> datas;

    public RecyclerAdapter(ArrayList<Memo> datas){

    this.datas =datas;

    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);



        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Memo memo = datas.get(position);
        holder.setTitle(memo.getContent());
        holder.setData(memo.getDate());
        holder.setDocument_id(memo.getId());
    }

    @Override
    public int getItemCount() {

        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        private  TextView title,data;

        private  String documentId;

        public Holder(View itemView) {

            super(itemView);

            title =(TextView) itemView.findViewById(R.id.title);
            data = (TextView)itemView.findViewById(R.id.data);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DetailActivity.class);
                    intent.putExtra(DetailActivity.DOC_KEY_NAME,documentId);
                   v.getContext().startActivity(intent);


                }
            });
        }
        public void  setTitle(String value){
            title.setText(value);
        }
        public void  setData(String value){
            data.setText(value);

        }
        public void setDocument_id(String value){
            documentId=value;

        }





    }

}
