/**
 * This class represents User Roles and their respective rights on the forms
 */

package org.irdresearch.tbreach.mobile.constants;

/**
 * @author Owais
 * 
 */
public final class UserRights
{
	private static int[]	adminRights			= {MenuItem.MENU_SUSPECT_ID};
	private static int[]	cehwRights			= {};
	private static int[]	chwRights			= {MenuItem.MENU_SUSPECT_ID};
	private static int[]	glwRights			= {};
	private static int[]	gpRights			= {};
	private static int[]	supervisorRights	= {};
	private static int[]	laboratoryRights	= {};
	private static int[]	tswRights			= {};

	public static boolean getUserRights (int userType, int menuItem)
	{
		boolean b = false;
		int[] toUse = {};
		switch (userType)
		{
			case UserType.USER_TYPE_ADMIN :
				toUse = adminRights;
				break;
			/*case UserType.CEHW :
				toUse = cehwRights;
				break;*/
			case UserType.USER_TYPE_CHW :
				toUse = chwRights;
				break;
			/*case UserType.GLW :
				toUse = glwRights;
				break;
			case UserType.GP :
				toUse = gpRights;
				break;
			case UserType.LABORATORY :
				toUse = laboratoryRights;
				break;
			case UserType.SUPERVISOR :
				toUse = supervisorRights;
				break;
			case UserType.TSW :
				toUse = tswRights;
				break;*/
			default :
				break;
		}
		for (int i = 0; i < toUse.length; i++)
			if (menuItem == toUse[i])
				b = true;
		return b;
	}
}
