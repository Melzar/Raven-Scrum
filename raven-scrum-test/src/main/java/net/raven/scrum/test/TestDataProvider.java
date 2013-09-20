package net.raven.scrum.test;

import java.util.Collection;

public interface TestDataProvider
{
	public void loadDataByLine(String datafile);

	public Collection<String> getDataByLine();

	public void clearData();
}
