package com.questions.regex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Sample Input:
 * 2015-03-03 15:52:15,021 - [INFO] - User id '12323' ordered '1' tickets for event 'Leonore Jazz Quartet' at venue 'SF Jazz'
 * 2015-03-03 15:52:15,021 - [INFO] - User id '12323' ordered 'abc' tickets for event 'Leonore Jazz Quartet' at venue 'SF Jazz'
 * 2015-03-03 15:52:15,026 - [INFO] - User id '89883' ordered '1 tickets for event 'La Estrella' at venue 'Casa de la Musica'
 * 2015-03-03 15:54:14,027 - [INFO] -Batch order of 2 tickets for venue Casa de la musica
 * 2015-03-03 15:54:14,027 - [INFO] -refund of 1 tickets for venue "Casa de la musica"
 * 
 * @author Ram Komma
 *
 */
public class LogParser {
	private static final String regEx = ".*(ordered|Batch|Refund).*(\\d).*(venue)\\W*(.*)\\W"; 
	private static final int flags = Pattern.CASE_INSENSITIVE;
	private Pattern pattern = null;
	private Map<String, Integer> venueTicketsMap;
	private Map<String, String> venueNamesMap;
	public LogParser()
	{
		pattern = Pattern.compile(regEx, flags);
		//LinkedHashMap to honor the insertion order
		venueTicketsMap = new LinkedHashMap<String, Integer>();
		venueNamesMap  = new LinkedHashMap<String, String>();
	}
	
	public void parser(String input)
	{
    	Matcher m = pattern.matcher(input);
    	if(m.matches())
    	{
    		try{
    			String action = m.group(1);
        		Integer ticket = Integer.parseInt(m.group(2));
        		String venue = m.group(4);
    			
        		if(action.equals("Refund"))
    				updateMaps(venue, ticket*(-1));
    			else
    				updateMaps(venue, ticket);
    		}
    		catch(Exception e)
    		{
    			//Do Nothing
    		}
    	}
    	return;
	}
	private void updateMaps(String venue,Integer tickets )
	{
		String key = venue.toUpperCase();
		//Update Display map
		if(venueNamesMap.get(key) == null)
			venueNamesMap.put(key, venue);
		//Update Tickets count map
		if(venueTicketsMap.get(key)!= null)
			tickets = tickets +(Integer)venueTicketsMap.get(key);
		venueTicketsMap.put(key, tickets);
	}
	private void printTicketsSoldPerEvent()
	{
		for(String key:venueNamesMap.keySet())
		{
			int tickets = venueTicketsMap.get(key);
			if(tickets != 0)
				System.out.println(venueNamesMap.get(key)+ ": "+ tickets);
		}
		
	}
	
	public void logParser()
	{
        Scanner scanner = null;
        try {
            //the input file will be provided through System.in, the scanner allows you to read it line by line
            scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parser(line); 
            }
            scanner.close();
            printTicketsSoldPerEvent();
        } finally{
            scanner.close();
        }
	}
	
    public static void main(String[] args) {

    	LogParser s = new LogParser();
    	s.logParser();
    	
    }
    
    
    
}