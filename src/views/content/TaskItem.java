package views.content;

import controller.Controller;
import models.Status;
import models.Task;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TaskItem extends JPanel implements Transferable, DragGestureListener {
    private DragSource source;
    private final Controller controller;
    private Task task;
    public static final DataFlavor FLAVOR = new DataFlavor(Task.class, "Task");

    public TaskItem(final Controller controller, final Task task) {
        this.controller = controller;
        this.task = task;
        setOpaque(false);
        setLayout(new MigLayout("", "[][grow][]"));

        final JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(task.isDone());
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                task.setDone(checkBox.isSelected());
                controller.save(task);
            }
        });
        add(checkBox);
        JLabel label = new JLabel(task.getDescription());
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) return;
                
                if(mouseEvent.getButton() == 1) {
                    //Left clicked
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

        add(new JLabel("#"+task.getContext()));

        final JComboBox statuses = new JComboBox(controller.getStatuses().toArray());
        statuses.setSelectedItem(task.getStatus());
        add(statuses);
        statuses.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                task.setStatus((Status) statuses.getSelectedItem());
                controller.save(task);
            }
        });

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
        source.startDrag(dragGestureEvent, DragSource.DefaultMoveDrop, this, new DragSourceAdapter() {
            @Override
            public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
                controller.refreshTasks();
            }
        });
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{FLAVOR};
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
