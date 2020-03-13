package br.com.tgob.testeandroid.adapter;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.tgob.testeandroid.R;
import br.com.tgob.testeandroid.activity.AccountDetailActivity;
import br.com.tgob.testeandroid.entity.StatementList;

@RequiresApi(api = Build.VERSION_CODES.N)
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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(statementLists.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.applyPattern("dd/MM/yyyy");
        String newDate = df.format(date);
        holder.tvDate.setText(newDate);

        NumberFormat format = NumberFormat.getCurrencyInstance();
        holder.tvValue.setText(format.format(statementLists.get(position).getValue()));
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
