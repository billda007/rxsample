package ackee.rxexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ackee.rxexample.R;
import ackee.rxexample.model.MergedEntity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Adapter for list of timesheets
 * Created by David Bilik[david.bilik@ackee.cz] on {30. 6. 2015}
 **/
public class TimesheetAdapter extends RecyclerView.Adapter<TimesheetAdapter.ViewHolder> {
    public static final String TAG = TimesheetAdapter.class.getName();

    private List<MergedEntity> mData;

    public TimesheetAdapter(List<MergedEntity> data) {
        this.mData = data;
    }

    @Override
    public TimesheetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timesheet, parent, false));
    }

    @Override
    public void onBindViewHolder(TimesheetAdapter.ViewHolder holder, int position) {
        MergedEntity entity = mData.get(position);
        holder.mTxtIssueDesc.setText(entity.getIssue().getDescription());
        holder.mTxtIssueNumber.setText("#" + entity.getTimesheet().getRedmineIssueId() + " - " + entity.getIssue().getSubject());
        holder.mTxtTimeSheetName.setText(entity.getTimesheet().getTimesheetName());
        holder.mTxtSpentHours.setText(String.format("%.02f h", entity.getTimesheet().getSpentHours()));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.txt_issue_description)
        TextView mTxtIssueDesc;
        @InjectView(R.id.txt_issue_number)
        TextView mTxtIssueNumber;

        @InjectView(R.id.txt_spent_hours)
        TextView mTxtSpentHours;
        @InjectView(R.id.txt_timesheet_name)
        TextView mTxtTimeSheetName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
