package br.com.tgob.testeandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.tgob.testeandroid.R;
import br.com.tgob.testeandroid.activity.AccountDetailActivity;
import br.com.tgob.testeandroid.entity.StatementList;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.UserDetailViewHolder > {
    private List<StatementList> statementLists;

    public List<StatementList> statementLists() {
        return statementLists;
    }

    public UserDetailAdapter(AccountDetailActivity accountDetailActivity, List<StatementList> statementLists) {
            this.statementLists = statementLists;
    }

    @NonNull
    @Override
    public UserDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_userdetails, parent, false);
        return new UserDetailViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailViewHolder holder, int position) {
            holder.tvPayment.setText(statementLists.get(position).getTitle());
            holder.tvBills.setText(statementLists.get(position).getDesc());
            holder.tvDate.setText(statementLists.get(position).getDate());
            holder.tvValue.setText(statementLists.get(position).getValue().toString());
    }

    @Override
    public int getItemCount() {
        return statementLists.size();
    }

    public class UserDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPayment;
        public TextView tvBills;
        public TextView tvDate;
        public TextView tvValue;

        public UserDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPayment = itemView.findViewById(R.id.tvPayment);
            tvBills = itemView.findViewById(R.id.tvBills);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvValue = itemView.findViewById(R.id.tvValue);
        }
    }
}
