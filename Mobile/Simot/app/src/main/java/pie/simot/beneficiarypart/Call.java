package pie.simot.beneficiarypart;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by elysi on 2/18/2017.
 */

public class Call implements Parcelable{
    private String postTitle;
    private String messageBody;
    private String orgName;
    private int urgencyLevel;

    public Call(){}
    protected Call(Parcel in) {
        postTitle = in.readString();
        messageBody = in.readString();
        orgName = in.readString();
        urgencyLevel = in.readInt();
    }

    public static final Creator<Call> CREATOR = new Creator<Call>() {
        @Override
        public Call createFromParcel(Parcel in) {
            return new Call(in);
        }

        @Override
        public Call[] newArray(int size) {
            return new Call[size];
        }
    };

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postTitle);
        dest.writeString(messageBody);
        dest.writeString(orgName);
        dest.writeInt(urgencyLevel);
    }
}
