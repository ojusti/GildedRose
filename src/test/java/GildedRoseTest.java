import java.util.ArrayList;
import java.util.List;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;


public class GildedRoseTest {

	private GildedRose gildedRose;
	private List<String> states;
	private int N;
	
	@Before
	public void createDefault() {
		gildedRose = GildedRose.makeDefault();
		N = 27;
		states = new ArrayList<>(N);
	}
	
	@Test
	public void lockDown() throws Exception {
		updateQualityManyTimes(gildedRose);
		Approvals.verify(states.toString());
	}
	
	private void updateQualityManyTimes(GildedRose gildedRose) {
		
		for(int i = 0; i < N; i++)
		{
			gildedRose.updateQuality();
			String newState = gildedRose.toString();
			if(isStable(newState))
			{
				return;
			}
			states.add(newState);
		}
	}
	private boolean isStable(String newState) {
		if(states.isEmpty())
		{
			return false;
		}
		String lastState = states.get(states.size() - 1);
		return newState.equals(lastState);
	}
}
