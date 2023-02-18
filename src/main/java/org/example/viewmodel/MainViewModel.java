package org.example.viewmodel;

import org.example.sidepage.SidebarPage;
import org.example.sidepage.SidebarPageConfig;
import org.example.sidepage.SidebarPageConfigAjaxbasedImpl;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class MainViewModel extends SelectorComposer<Component> {
    @Wire
    Grid sidebar;

    @Wire
    Include includeMainContent;

    SidebarPageConfig pageConfig = new SidebarPageConfigAjaxbasedImpl();

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        Rows rows = sidebar.getRows();

        for (SidebarPage page : pageConfig.getPages()) {
            Row row = constructSidebarRow(page.getName(), page.getLabel(), page.getIconUri(), page.getUri());
            rows.appendChild(row);
        }
    }

    private Row constructSidebarRow(String name, String label, String imageSrc, final String locationUri) {

        Row row = new Row();
        Image image = new Image(imageSrc);
//        Label image = new Label("-");
        Label lab = new Label(label);

        row.appendChild(image);
        row.appendChild(lab);

        row.setSclass("sidebar-fn");

        EventListener<Event> onActionListener = new SerializableEventListener<Event>() {
            private static final long serialVersionUID = 1L;

            public void onEvent(Event event) throws Exception {
                if (locationUri.startsWith("http")) {
                    Executions.getCurrent().sendRedirect(locationUri);
                } else {
                    includeMainContent.setSrc(locationUri);
                }
            }
        };
        row.addEventListener(Events.ON_CLICK, onActionListener);

        return row;
    }
}
