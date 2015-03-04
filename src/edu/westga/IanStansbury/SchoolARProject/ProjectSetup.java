package edu.westga.IanStansbury.SchoolARProject;

import geo.GeoObj;
import gl.Color;
import gl.CustomGLSurfaceView;
import gl.GLCamera;
import gl.GLFactory;
import gl.GLRenderer;
import gl.scenegraph.MeshComponent;
import gl.scenegraph.Shape;
import gui.GuiSetup;
import gui.InfoScreenSettings;
import system.ErrorHandler;
import system.EventManager;
import system.Setup;
import util.Vec;
import worldData.Obj;
import worldData.SystemUpdater;
import worldData.World;
import actions.ActionCalcRelativePos;
import actions.ActionMoveCameraBuffered;
import actions.ActionRotateCameraBuffered;
import android.app.Activity;
import android.location.Location;

import commands.Command;
import commands.ui.CommandShowToast;
import components.ProximitySensor;

import de.rwth.R;

/**
 * Project Setup is the class that executes and handles logic from both the
 * phone hardware and the logic for spawning location points
 * 
 * @author ian
 * 
 */
public class ProjectSetup extends Setup {

	// data members
	private GLCamera camera;
	private World world;
	private int catchedNumber;
	private ActionCalcRelativePos gpsAction;
	private GeoObj posA;

	/**
	 * initialization method, called after all views have been loaded. allows
	 * error reports to be sent email address
	 */
	@Override
	public void _a_initFieldsIfNecessary() {
		// allow the user to send error reports to the developer:
		ErrorHandler.enableEmailReports("ian.3001@hotmail.com",
				"Error in ProjectSetup");

	}

	/**
	 * constructor for calling the setup object
	 * 
	 * @param pos
	 *            a GeoObj that contains longitude, latitude, elevation, and
	 *            scale for item pickup
	 */
	public ProjectSetup(GeoObj pos) {
		camera = new GLCamera();
		world = new World(camera);
		gpsAction = new ActionCalcRelativePos(world, camera);
		posA = pos;
	}

	/**
	 * creates the world renderer
	 * 
	 * @param renderer
	 *            GL function for creating overlay for phone
	 * @param objectFactory
	 *            used for creating three dimensional objects to be rendered
	 * @param currentPosistion
	 *            gives the current position of the phone
	 */
	@Override
	public void _b_addWorldsToRenderer(GLRenderer renderer,
			GLFactory objectFactory, GeoObj currentPosition) {

		// spawns the object, in this case an arrow, at the GeoObj position posA
		spawnObj(posA, GLFactory.getInstance().newArrow());

		/**
		 * setComp set comparision - allows for checking of relative position
		 * against the current phone position, within range of 60f
		 */
		posA.setComp(new ProximitySensor(camera, 60f) {
			@Override
			public void onObjectIsCloseToCamera(GLCamera myCamera2, Obj obj,
					MeshComponent m, float currentDistance) {
				// toast to show when destination is reached
				new CommandShowToast(myTargetActivity,
						"You have arrived at your destination!").execute();
			}
		});

		// adds posA to world
		world.add(posA);
		// renders world
		renderer.addRenderElement(world);
	}

	/**
	 * action handler, controls the buffering effects for moving the camera
	 * around
	 */
	@Override
	public void _c_addActionsToEvents(EventManager eventManager,
			CustomGLSurfaceView arView) {
		ActionMoveCameraBuffered move = new ActionMoveCameraBuffered(camera, 5,
				25);
		eventManager
				.addOnOrientationChangedAction(new ActionRotateCameraBuffered(
						camera));
		eventManager.addOnLocationChangedAction(new ActionCalcRelativePos(
				world, camera));
	}

	/**
	 * update thread, only updates the rendering of the world
	 */
	@Override
	public void _d_addElementsToUpdateThread(SystemUpdater worldUpdater) {

		worldUpdater.addObjectToUpdateCycle(world);

	}

	// accessor for getting the camera
	public GLCamera getCamera() {
		return camera;
	}

	/**
	 * Adds elements such as buttons and check boxes to UI overlay above camera
	 * and gl interfaces
	 */
	@Override
	public void _e2_addElementsToGuiSetup(GuiSetup guiSetup, Activity context) {

		// gives location of phone on button press
		guiSetup.addButtonToLeftView(new Command() {

			@Override
			public boolean execute() {
				// pops message for the current longitude and latitude
				CommandShowToast
						.show(getActivity(),
								"Latitude="
										+ getCamera().getGPSPositionVec().y
										+ "Longitude="
										+ getCamera().getGPSPositionVec().x
										+ "Elevation="
										+ (getCamera().getGPSPositionVec().z * 3.28083989501312)
										+ " feet" + "Elav="
										+ (getCamera().getGPSPositionVec().z));

				return true;
			}
		}, "Find Location");

		// gives location of spawned object on button press
		guiSetup.addButtonToLeftView(new Command() {

			@Override
			public boolean execute() {
				// pops message for the current longitude and latitude
				CommandShowToast.show(
						getActivity(),
						"Latitude=" + posA.getLatitude() + "Longitude="
								+ posA.getLongitude());

				return true;
			}
		}, "Find object location");

		// gets distance from spawned object to current phone position
		guiSetup.addButtonToLeftView(new Command() {

			@Override
			public boolean execute() {
				// pops message for the current longitude and latitude
				CommandShowToast.show(getActivity(), "Distance to Target:"
						+ getDistance());

				return true;
			}
		}, "Distance to Object");
	}

	/**
	 * Adds info screen to when setup is opened
	 */
	@Override
	public void _f_addInfoScreen(InfoScreenSettings infoScreenData) {

		infoScreenData
				.addText("Welcome to the lost Freshman Camera Interface!");
		infoScreenData
				.addTextWithIcon(
						R.drawable.infoboxblue,
						"Swing your camera view around until your destination appears on the screen. Follow that to your current destination.");
	}

	/**
	 * calculates the distance to the spawned object from the camera view
	 * 
	 * @return distance to target
	 */
	public double getDistance() {
		double distance;
		// base of distance formula
		distance = Math
				.sqrt(Math.pow((getCamera().getGPSPositionVec().x - posA
						.getLongitude()), 2)
						+ Math.pow((getCamera().getGPSPositionVec().y - posA
								.getLatitude()), 2));
		// miles in a degree for our location, changes as you travel north or
		// south
		distance = distance * 57;
		// feet in a mile
		distance = distance * 5280;
		return distance;

	}

	/**
	 * Method for spawning object, creates object, sets mesh(openGL), and sets
	 * mesh to object. Then adds to world
	 * 
	 * @param pos
	 *            position of object
	 * @param mesh
	 *            OpenGl mesh component
	 */
	private void spawnObj(final GeoObj pos, MeshComponent mesh) {
		GeoObj x = new GeoObj(pos);

		mesh.setPosition(Vec.getNewRandomPosInXYPlane(new Vec(), 0.1f, 1f));
		x.setComp(mesh);
		CommandShowToast.show(myTargetActivity, "Object spawned at "
				+ x.getMySurroundGroup().getPosition());
		world.add(x);
	}

}
