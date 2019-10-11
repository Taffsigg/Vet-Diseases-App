package vetzwe.animaldiseases.adapters;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.databases.DatabaseQuery;

public class RecyclerDialogFragment extends DialogFragment implements ILaunchActivity {

    private RecyclerView recyclerView;
    private RecyclerDialogAdapter recyclerDialogAdapter;

    private List<String> phones;

    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        id = bundle.getInt("ID");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_dialog, container);

        phones = new DatabaseQuery(RecyclerDialogFragment.this.getActivity()).getPhones(id);

        TextView tv_rec_dialog1 = rootView.findViewById(R.id.tv_rec_dialog1);
        TextView tv_rec_dialog2 = rootView.findViewById(R.id.tv_rec_dialog2);

        tv_rec_dialog1.setText(new DatabaseQuery(RecyclerDialogFragment.this.getActivity()).getProvince
                (id).getProvince());

        recyclerView = rootView.findViewById(R.id.recvw_dialog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        recyclerDialogAdapter = new RecyclerDialogAdapter(RecyclerDialogFragment.this.getActivity
                ().getApplicationContext(), this, phones, this);
        recyclerView.setAdapter(recyclerDialogAdapter);

        return rootView;
    }

    @Override
    public void launchActivity(String selectedItem) {
        Intent intent = new Intent((Intent.ACTION_DIAL));
        intent.setData(Uri.parse("tel:" + selectedItem));
        startActivity(intent);
    }

    @Override
    public void launchActivity(int id) {

    }

}