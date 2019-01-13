package maf.adil.mirza.maf;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import maf.adil.mirza.maf.mvp.ui.news.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * @author Mirza Adil
 * @date 2019-01-13
 * This class create for instrument testing.
 */

public class NewsActivity {

@Rule
public ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureRecyclerViewIsPresentAndNotNull() throws Exception {
        MainActivity activity = activityTestRule.getActivity();
        View viewById = activity.findViewById(R.id.recycler_view_users);
        assertThat(viewById, notNullValue());
        assertThat(viewById, Matchers.<View>instanceOf(RecyclerView.class));
    }


    @Test
    public void ensureRecyclerViewISEnabled(){
        onView(withId(R.id.recycler_view_users)).check(matches((isEnabled())));
    }

    @Test
    public void ensureRecyclerViewItemIsClickable() {
        onView(withId(R.id.recycler_view_users)).check(matches(not(isClickable())));

    }

}
