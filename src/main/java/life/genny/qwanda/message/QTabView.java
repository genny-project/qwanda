package life.genny.qwanda.message;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import java.util.HashMap;

public class QTabView {

    @Expose
    private String title;

    @Expose
    private String icon;

    @Expose
    private QTabViewLayout layout;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setLayout(QTabViewLayout layout) {
        this.layout = layout;
    }

    public QTabViewLayout getLayout() {
        return this.layout;
    }
}
