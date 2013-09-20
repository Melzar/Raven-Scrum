package net.raven.scrum.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class TestDataProviderImpl implements TestDataProvider
{
	private Collection<String> dataByLine;

	public TestDataProviderImpl()
	{

	}

	@Override
	public void loadDataByLine(String datafile)
	{
		dataByLine = new ArrayList<String>();
		try
		{
			String line;
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(getClass().getClassLoader()
							.getResourceAsStream(datafile)));
			while ((line = bufferedreader.readLine()) != null)
			{
				if (line.startsWith("#"))
				{
					continue;
				}
				dataByLine.add(line);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("FALED TO READ DATA FROM FILE");
		}
	}

	@Override
	public Collection<String> getDataByLine()
	{
		return dataByLine;
	}

	@Override
	public void clearData()
	{
		dataByLine.clear();
	}

}
