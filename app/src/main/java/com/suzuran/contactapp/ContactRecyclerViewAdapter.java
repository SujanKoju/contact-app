package com.suzuran.contactapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {

    List<Contact> contactList;
    Context context;

    DataBaseHelper dataBaseHelper;

    public ContactRecyclerViewAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    // TO TELL WHICH VIEW HOLDER TO SHOW REPEATEDLY ON THE RECYCLER VIEW
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    // What happens after we create view holder object
    public void onBindViewHolder(@NonNull ContactRecyclerViewAdapter.ViewHolder holder, int position) {
        final Contact contact = contactList.get(position);
        holder.textViewId.setText(String.valueOf(contact.getId()));
        holder.editText_name.setText(contact.getName());
        holder.editText_phone.setText(contact.getPhone());

        holder.button_edit.setOnClickListener(v -> {
            Log.d("SUJAN", "-- EDIT BUTTON CLICKED --");
            String name = holder.editText_name.getText().toString();
            String phone = holder.editText_phone.getText().toString();
            dataBaseHelper.updateContact(new Contact(contact.getId(), name, phone));
            notifyDataSetChanged();
            ((Activity) context).finish();
            context.startActivity(((Activity) context).getIntent());
        });

        holder.button_delete.setOnClickListener(v -> {
            Log.d("SUJAN", "-- DELETE BUTTON CLICKED --");
            dataBaseHelper.deleteContact(contact.getId());
            contactList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    //how many view holder objects to create ???
    public int getItemCount() {
        return contactList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        EditText editText_name, editText_phone;
        Button button_edit, button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textView_id);
            editText_name = itemView.findViewById(R.id.textView_name);
            editText_phone = itemView.findViewById(R.id.textView_phone);
            button_edit = itemView.findViewById(R.id.button_edit);
            button_delete = itemView.findViewById(R.id.button_delete);
        }

    }
}
