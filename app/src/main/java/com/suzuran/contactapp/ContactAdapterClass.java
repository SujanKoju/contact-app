package com.suzuran.contactapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapterClass extends RecyclerView.Adapter<ContactAdapterClass.ViewHolder> {

    List<Contact> contactList;
    Context context;
    DataBaseHelper dataBaseHelper;

    public ContactAdapterClass(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapterClass.ViewHolder holder, int position) {
        final Contact contact = contactList.get(position);
        holder.textViewId.setText(String.valueOf(contact.getId()));
        holder.editText_name.setText(contact.getName());
        holder.editText_phone.setText(contact.getPhone());

        holder.button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = holder.editText_name.getText().toString();
                String phone = holder.editText_phone.getText().toString();
                dataBaseHelper.updateContact(new Contact(contact.getId(), name, phone));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteContact(contact.getId());
                contactList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
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
