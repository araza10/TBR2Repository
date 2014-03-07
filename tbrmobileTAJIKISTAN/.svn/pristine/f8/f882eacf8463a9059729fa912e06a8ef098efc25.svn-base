package org.irdresearch.tbreach.mobile.util;

public class IdValidateUtil {
	public static final int	MAX_PILL_DAYS			= 14;
	public static final int	MAX_STREPTO_DAYS		= 14;
	public static final int	MAX_SYRUP_DAYS			= 14;

	public static final int	MAX_PILL_WEEKS			= 2;
	public static final int	MAX_STREPTO_WEEKS		= 2;
	public static final int	MAX_SYRUP_WEEKS			= 2;
	
	public static final int	MAX_PILL_MONTHS			= 1;
	public static final int	MAX_STREPTO_MONTHS		= 1;
	public static final int	MAX_SYRUP_MONTHS		= 1;
	
	public static final int	MAX_PAT_LENGTH			= 11;
	public static final int	MIN_PAT_LENGTH			= 11;
	public static final int	MAX_GP_ID_LENGTH		= 11;
	public static final int	MIN_GP_ID_LENGTH		= 8;

	public static final int	MAX_GP_NUM				= 70;
	public static final int	YEAR					= 11;
	public static final int	OTHER_YEAR				= 12;

	public static final int	ID_VALID				= 0;
	public static final int	BAD_ID_LENGTH			= 1;
	public static final int	BAD_GP_NUMBER			= 2;
	public static final int	BAD_YEAR				= 3;
	public static final int	MAX_ID_LENGTH			= 11;
	public static final int	MIN_ID_LENGTH			= 8;

	public static final int	MAX_CHWID_LENGTH		= 33;
	public static final int	MIN_CHWID_LENGTH		= 5;
	public static final int	MAX_MONITORID_LENGTH	= 11;
	public static final int	MIN_MONITORID_LENGTH	= 8;

	
	public static int validateId(String id) {
		
		if(id==null || id.length()>MAX_PAT_LENGTH || id.length()< MIN_PAT_LENGTH) {
			return BAD_ID_LENGTH;
		}
		
		String gpNum = id.substring(0,2);
		/*int gpNumInt = Integer.parseInt(gpNum);
		
		if(gpNumInt > MAX_GP_NUM) {
			return BAD_GP_NUMBER;
		}*/
		
		String yearNum = id.substring(3,5);
		int yearNumInt = Integer.parseInt(yearNum);
		
		if(yearNumInt!=YEAR && yearNumInt != OTHER_YEAR) {
			return BAD_YEAR;
		}
				
		return ID_VALID;
	}

}
