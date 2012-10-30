package views.content;

import controller.Controller;
import models.Status;
import models.Task;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;


public class TaskItem extends JPanel implements Transferable, DragGestureListener {
    private DragSource source;
    private Task task;

    public TaskItem(final Controller controller, final Task task) {
        this.task = task;
        setOpaque(false);
        setLayout(new MigLayout("", "[][grow][]"));

        add(new JCheckBox());
        JLabel label = new JLabel(task.getDescription());
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == 1) {
                    //Left clicked
                    if (mouseEvent.isPopupTrigger()) return;
                    removeAll();
                    setLayout(new MigLayout("", "[grow]"));
                    add(new TaskForm(controller, task), "grow");
                    revalidate();
                    repaint();
                }else if(mouseEvent.getButton() == 3 ) {
                    //Right clicked
                }
            }
        });
        add(label, "growx");

        add(new JLabel(task.getContext()));

        JComboBox statuses = new JComboBox(controller.getStatuses().toArray());
        statuses.setSelectedItem(task.getStatus());
        add(statuses);

        String date = "";
        if(task.getActionDate() != null)
            date = task.getActionDate().toString();
        add(new JLabel(date), "span");

        source = new DragSource();
        source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
    }

    @Override
    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        Dimension originalSize = super.getSize();

        // rounded corners
        g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, originalSize.width, originalSize.height, 10, 10);
        // draw the rest
        super.paint(g);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
        source.startDrag(dragGestureEvent, DragSource.DefaultMoveDrop, this, new DragSourceAdapter() {});
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{new DataFlavor(Task.class, "Task")};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
        return true;
    }

    @Override
    public Object getTransferData(DataFlavor dataFlavor) {
        return task;
    }
}
