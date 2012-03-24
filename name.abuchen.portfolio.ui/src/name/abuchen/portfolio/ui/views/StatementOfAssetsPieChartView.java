package name.abuchen.portfolio.ui.views;

import java.util.ArrayList;
import java.util.List;

import name.abuchen.portfolio.model.Security.AssetClass;
import name.abuchen.portfolio.snapshot.AssetCategory;
import name.abuchen.portfolio.snapshot.ClientSnapshot;
import name.abuchen.portfolio.ui.Messages;
import name.abuchen.portfolio.ui.util.Colors;
import name.abuchen.portfolio.ui.util.PieChart;
import name.abuchen.portfolio.util.Dates;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StatementOfAssetsPieChartView extends AbstractListView
{

    private PieChart pieChart;

    @Override
    protected String getTitle()
    {
        return Messages.LabelStatementOfAssetsClasses;
    }

    protected void createTopTable(Composite parent)
    {
        pieChart = new PieChart(parent, SWT.NONE);

        ClientSnapshot snapshot = ClientSnapshot.create(getClient(), Dates.today());
        List<AssetCategory> categories = snapshot.groupByCategory();

        List<PieChart.Slice> slices = new ArrayList<PieChart.Slice>();

        for (AssetClass a : AssetClass.values())
            slices.add(new PieChart.Slice(categories.get(a.ordinal()).getValuation(), a.name(),
                            Colors.valueOf(a.name())));

        pieChart.setSlices(slices);
        pieChart.redraw();
    }

    @Override
    protected void createBottomTable(Composite parent)
    {}

}
