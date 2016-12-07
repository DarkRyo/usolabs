package py.edu.uaa.usolabs.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

import py.edu.uaa.usolabs.dao.InstructorDao;
import py.edu.uaa.usolabs.dao.LaboratorioDao;
import py.edu.uaa.usolabs.dao.ReservaDao;
import py.edu.uaa.usolabs.model.Instructor;
import py.edu.uaa.usolabs.model.Laboratorio;
import py.edu.uaa.usolabs.model.Persona;
import py.edu.uaa.usolabs.model.Reserva;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;

public class ReservaLabView {

	private JFrame frmRegistroReserva;
	private JTextField txtHoraInicio;
	private JTextField txtHoraFin;
	private JTextField txtCodReserva;
	private JTextField txtCurso;

	// private JComboBox cboLab;
	// private JComboBox cboInstructor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaLabView window = new ReservaLabView();
					window.frmRegistroReserva.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservaLabView() {
		initialize();
	}

	/////////////// BTN CONFIRMAR

	public void confirmar() {
		Object[] opciones = { "Aceptar", "Cancelar" };
		int eleccion = JOptionPane.showOptionDialog(null, "¿Desea cancelar?", "Mensaje de Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/////////////// BTN CONFIRMAR
	private void initialize() {

		// instancia de los objetos del cbo
		InstructorDao insdao = new InstructorDao();
		LaboratorioDao labdao = new LaboratorioDao();

		frmRegistroReserva = new JFrame();
		frmRegistroReserva.getContentPane().setBackground(Color.ORANGE);
		frmRegistroReserva.setBounds(100, 100, 563, 361);
		frmRegistroReserva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistroReserva.getContentPane().setLayout(null);

		JLabel lblRegistroDeReservas = new JLabel("Registro de Reservas");
		lblRegistroDeReservas.setBounds(42, 11, 440, 43);
		lblRegistroDeReservas.setForeground(Color.GRAY);
		lblRegistroDeReservas.setBackground(Color.YELLOW);
		lblRegistroDeReservas.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblRegistroDeReservas.setHorizontalAlignment(SwingConstants.CENTER);
		frmRegistroReserva.getContentPane().add(lblRegistroDeReservas);

		JLabel lblCodigoReserva = new JLabel("Codigo Reserva");
		lblCodigoReserva.setBounds(10, 73, 87, 14);
		frmRegistroReserva.getContentPane().add(lblCodigoReserva);

		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setBounds(10, 98, 87, 14);
		frmRegistroReserva.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Hora Inicio");
		lblNewLabel_1.setBounds(10, 123, 71, 24);
		frmRegistroReserva.getContentPane().add(lblNewLabel_1);

		JLabel lblHoraFin = new JLabel("Hora Fin");
		lblHoraFin.setBounds(10, 158, 71, 14);
		frmRegistroReserva.getContentPane().add(lblHoraFin);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(10, 189, 46, 14);
		frmRegistroReserva.getContentPane().add(lblCurso);

		JLabel lblInstructor = new JLabel("Instructor");
		lblInstructor.setBounds(300, 73, 71, 14);
		frmRegistroReserva.getContentPane().add(lblInstructor);

		JDateChooser dtpFecha = new JDateChooser();
		dtpFecha.setBackground(Color.LIGHT_GRAY);
		dtpFecha.setBounds(107, 98, 129, 20);
		frmRegistroReserva.getContentPane().add(dtpFecha);

		//////////////////////////////////////////////////////////////////////////////////////
		List<Instructor> instructores = insdao.recuperarInstructores();
		ArrayList<String> stringComboInstructor = new ArrayList();
		for (Instructor instructor : instructores) {
			stringComboInstructor.add(instructor.getNombreApellido_ins());
		}
		JComboBox<Instructor> cboinstructor = new JComboBox(stringComboInstructor.toArray());
		cboinstructor.setBounds(367, 70, 134, 29);
		frmRegistroReserva.getContentPane().add(cboinstructor);

		/////////////////////////////////////////////////////////////////////////////

		List<Laboratorio> laboratorios = labdao.recuperarLaboratorio();
		ArrayList<String> stringComboLaboratorio = new ArrayList();
		for (Laboratorio laboratorio : laboratorios) {
			stringComboLaboratorio.add(laboratorio.getDescripcion());
		}
		JComboBox<Laboratorio> cbolaboratorio = new JComboBox(stringComboLaboratorio.toArray());
		cbolaboratorio.setBounds(367, 125, 129, 22);
		frmRegistroReserva.getContentPane().add(cbolaboratorio);

		JButton btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					Reserva reservalab = new Reserva();
					ReservaDao reservalabDao = new ReservaDao();

					// Fechaaaa
					Date date = dtpFecha.getDate();
					String fecha = DateFormat.getDateInstance().format(date);
					reservalab.setFechaReserva(fecha);

					// combobox INS
					Instructor ins = new Instructor();
					ins.setNombreApellido_ins(cboinstructor.getSelectedItem().toString());
					reservalab.setCod_instructor(ins);

					// combobox LAB
					Laboratorio lab = new Laboratorio();
					lab.setDescripcion(cbolaboratorio.getSelectedItem().toString());
					reservalab.setCod_laboratorio(lab);

					// codigo reserva
					reservalab.setCod_reserva(txtCodReserva.getText().toString());

					// hora inicio
					reservalab.setHoraInicio(txtHoraInicio.getText().toString());

					// hora fin
					reservalab.setHoraFin(txtHoraFin.getText().toString());

					// curso
					reservalab.setCod_curso(txtCurso.getText().toString());

					Boolean isInserted = reservalabDao.insertarReserva(reservalab);

					if (isInserted) {
						JOptionPane.showMessageDialog(null, "REGISTRADO CORRECTAMENTE", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "NO SE PUDO REGISTRAR", "", JOptionPane.ERROR_MESSAGE,
								null);
					}

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			}
		});

		
		btnAñadir.setBounds(29, 245, 78, 36);
		frmRegistroReserva.getContentPane().add(btnAñadir);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(145, 245, 87, 36);
		frmRegistroReserva.getContentPane().add(btnActualizar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Reserva reservalab = new Reserva();
				ReservaDao reservalabDao = new ReservaDao();
	
				// Fechaaaa
				Date date = dtpFecha.getDate();
				String fecha = DateFormat.getDateInstance().format(date);
				reservalab.setFechaReserva(fecha);

				// combobox INS
				Instructor ins = new Instructor();
				ins.setNombreApellido_ins(cboinstructor.getSelectedItem().toString());
				reservalab.setCod_instructor(ins);

				// combobox LAB
				Laboratorio lab = new Laboratorio();
				lab.setDescripcion(cbolaboratorio.getSelectedItem().toString());
				reservalab.setCod_laboratorio(lab);

				// codigo reserva
				reservalab.setCod_reserva(txtCodReserva.getText().toString());

				// hora inicio
				reservalab.setHoraInicio(txtHoraInicio.getText().toString());

				// hora fin
				reservalab.setHoraFin(txtHoraFin.getText().toString());

				// curso
				reservalab.setCod_curso(txtCurso.getText().toString());

				ReservaDao eliReservaDao = new ReservaDao();
				Boolean isDeleted = eliReservaDao.eliminarReserva(reservalab);

				if (isDeleted) {
					JOptionPane.showMessageDialog(null, "Eliminado correctamente");

				} else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar", null, JOptionPane.ERROR_MESSAGE, null);

				}
			}
		});
		btnEliminar.setBounds(282, 245, 87, 36);
		frmRegistroReserva.getContentPane().add(btnEliminar);

		txtHoraInicio = new JTextField();
		txtHoraInicio.setBackground(Color.LIGHT_GRAY);
		txtHoraInicio.setBounds(105, 123, 113, 24);
		frmRegistroReserva.getContentPane().add(txtHoraInicio);
		txtHoraInicio.setColumns(10);

		txtHoraFin = new JTextField();
		txtHoraFin.setBackground(Color.LIGHT_GRAY);
		txtHoraFin.setColumns(10);
		txtHoraFin.setBounds(105, 155, 113, 24);
		frmRegistroReserva.getContentPane().add(txtHoraFin);

		txtCodReserva = new JTextField();
		txtCodReserva.setBackground(Color.LIGHT_GRAY);
		txtCodReserva.setBounds(107, 70, 86, 20);
		frmRegistroReserva.getContentPane().add(txtCodReserva);
		txtCodReserva.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Laboratorio");
		lblNewLabel_2.setBounds(300, 128, 71, 14);
		frmRegistroReserva.getContentPane().add(lblNewLabel_2);

		txtCurso = new JTextField();
		txtCurso.setBackground(Color.LIGHT_GRAY);
		txtCurso.setBounds(107, 186, 86, 20);
		frmRegistroReserva.getContentPane().add(txtCurso);
		txtCurso.setColumns(10);

		JButton btnNewButton = new JButton("Ver listado");
		btnNewButton.setBounds(388, 185, 113, 23);
		frmRegistroReserva.getContentPane().add(btnNewButton);

		///////////////////////////////////////

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmar();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		btnCancelar.setBounds(398, 245, 103, 36);
		frmRegistroReserva.getContentPane().add(btnCancelar);

	}
}
