import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

public class ImagePanel extends JPanel implements ActionListener {
	JPanel bar, board;
	JScrollPane sp;
	JToggleButton autor, autoh, autow, auton;
	JButton zoomin, zoomout;
	ImagePanel mirror = this;
	Image image, img;
	double factor;
	double scrh, scrv;
	int mode;

	ImagePanel() {
		mode = 0;
		factor = 1;

		setLayout(new BorderLayout());

		bar = new JPanel();
		autor = new JToggleButton("全自适应");
		autor.addActionListener(this);
		autor.setSelected(true);
		bar.add(autor);
		autoh = new JToggleButton("适应高度");
		autoh.addActionListener(this);
		bar.add(autoh);
		autow = new JToggleButton("适应宽度");
		autow.addActionListener(this);
		bar.add(autow);
		auton = new JToggleButton("无自适应");
		auton.addActionListener(this);
		bar.add(auton);
		add(bar, BorderLayout.NORTH);

		zoomin = new JButton("放大");
		zoomin.addActionListener(this);
		bar.add(zoomin);
		zoomout = new JButton("缩小");
		zoomout.addActionListener(this);
		bar.add(zoomout);
		board = new JPanel() {
			public void domode(){
				if(img!=null){
					;
				}
			}
			public void paint(Graphics g) {
				int tw = getWidth(), th = getHeight();
				g.setColor(getBackground());
				g.fillRect(0, 0, tw, th);
				if (img != null) {
					double r = (double) img.getHeight(this)
							/ (double) img.getWidth(this);
					Rectangle toshow = new Rectangle(0, 0, tw, th);
					switch (mode) {
					case 0:
						if (r > (double) th / (double) tw) {
							int w = (int) (th / r);
							g.drawImage(img, (tw - w) / 2, 0, w, th, this);
							factor = (double) th / (double) img.getHeight(this);
						} else {
							int h = (int) (tw * r);
							g.drawImage(img, 0, (th - h) / 2, tw, h, this);
							factor = (double) tw / (double) img.getWidth(this);
						}
						sp.revalidate();
						break;
					case 1:
						int w = (int) (th / r);
						setPreferredSize(new Dimension(w, 1));
						sp.doLayout();
						sp.revalidate();
						g.drawImage(img, (tw - w) / 2, 0, w, th, this);
						factor = (double) th / (double) img.getHeight(this);
						break;
					case 2:
						int h = (int) (tw * r);
						setPreferredSize(new Dimension(1, h));
						sp.doLayout();
						g.drawImage(img, 0, (th - h) / 2, tw, h, this);
						factor = (double) tw / (double) img.getWidth(this);
						break;
					case 3:
						int w1 = (int) ((double) img.getWidth(this) * factor),
						h1 = (int) ((double) img.getHeight(this) * factor);
						Rectangle rec = sp.getVisibleRect();
						toshow.setRect(rec.getCenterX() - tw / 2,
								rec.getCenterY() - th / 2, tw, th);
						setPreferredSize(new Dimension(w1, h1));
						sp.doLayout();
						sp.revalidate();
						g.drawImage(img, (tw - w1) / 2, (th - h1) / 2, w1, h1,
								this);
//						sp.getHorizontalScrollBar().setValue(
//								(int) (img.getWidth(this) * factor * scrh - sp
//										.getHorizontalScrollBar()
//										.getVisibleAmount() / 2));
//						sp.getVerticalScrollBar().setValue(
//								(int) (img.getHeight(this) * factor * scrv - sp
//										.getVerticalScrollBar()
//										.getVisibleAmount() / 2));
						break;
					default:
						sp.doLayout();
					}
					mirror.remove(sp);
					mirror.add(sp, BorderLayout.CENTER);
					JScrollBar hb = sp.getHorizontalScrollBar();
					scrh = (double) (hb.getValue() + hb.getVisibleAmount() / 2)
							/ (img.getWidth(this) * factor);
					hb = sp.getVerticalScrollBar();
					scrv = (double)(hb.getValue() + hb.getVisibleAmount() / 2)
							/ (double)(img.getHeight(this) * factor);
				}
			}
		};
		sp = new JScrollPane(board);
		sp.setPreferredSize(new Dimension(10, 10));
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(sp, BorderLayout.CENTER);
	}

	public void show(Image i) {
		img = i;
		// if (img.getHeight(this) / img.getWidth(this) > board.getHeight()
		// / board.getWidth())
		// image = img.getScaledInstance(-1, board.getHeight(),
		// Image.SCALE_SMOOTH);
		// else
		// image = img.getScaledInstance(board.getWidth(), -1,
		// Image.SCALE_SMOOTH);
		board.paint(board.getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object o = arg0.getSource();
		if (o == autor) {
			board.setPreferredSize(new Dimension(1, 1));
			sp.doLayout();
			sp.revalidate();
			autor.setSelected(true);
			autoh.setSelected(false);
			autow.setSelected(false);
			auton.setSelected(false);
			mode = 0;
			board.paint(board.getGraphics());
		} else if (o == autoh) {
			board.setPreferredSize(new Dimension(1, 1));
			sp.doLayout();
			sp.revalidate();
			autoh.setSelected(true);
			autor.setSelected(false);
			autow.setSelected(false);
			auton.setSelected(false);
			mode = 1;
			board.paint(board.getGraphics());
		} else if (o == autow) {
			board.setPreferredSize(new Dimension(1, 1));
			sp.doLayout();
			sp.revalidate();
			autow.setSelected(true);
			autoh.setSelected(false);
			autor.setSelected(false);
			auton.setSelected(false);
			mode = 2;
			board.paint(board.getGraphics());
		} else if (o == auton) {
			auton.setSelected(true);
			autoh.setSelected(false);
			autow.setSelected(false);
			autor.setSelected(false);
			mode = 3;
			board.paint(board.getGraphics());
		} else if (o == zoomin) {
			auton.setSelected(true);
			autoh.setSelected(false);
			autow.setSelected(false);
			autor.setSelected(false);
			mode = 3;
			factor *= 1.25;
			if (factor > 1)
				factor = 1;
			board.paint(board.getGraphics());
		} else if (o == zoomout) {
			auton.setSelected(true);
			autoh.setSelected(false);
			autow.setSelected(false);
			autor.setSelected(false);
			mode = 3;
			factor *= 0.8;
			if (factor < 0.1)
				factor = 0.1;
			board.paint(board.getGraphics());
		}
	}

}