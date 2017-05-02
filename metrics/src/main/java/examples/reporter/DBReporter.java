package examples.reporter;

import com.codahale.metrics.*;

import java.io.File;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 雷晓武 on 2017/4/28.
 */
public class DBReporter extends ScheduledReporter {
    protected DBReporter(MetricRegistry registry,Locale locale,
                         Clock clock,
                         TimeZone timeZone,
                         TimeUnit rateUnit,
                         TimeUnit durationUnit,
                         MetricFilter filter) {
        super(registry, "db-log", filter, rateUnit, durationUnit);
    }

    protected DBReporter(MetricRegistry registry, String name, MetricFilter filter, TimeUnit rateUnit, TimeUnit durationUnit, ScheduledExecutorService executor) {
        super(registry, name, filter, rateUnit, durationUnit, executor);
    }
    public static class Builder {
        private final MetricRegistry registry;
        private Locale locale;
        private TimeUnit rateUnit;
        private TimeUnit durationUnit;
        private Clock clock;
        private MetricFilter filter;

        private Builder(MetricRegistry registry) {
            this.registry = registry;
            this.locale = Locale.getDefault();
            this.rateUnit = TimeUnit.SECONDS;
            this.durationUnit = TimeUnit.MILLISECONDS;
            this.clock = Clock.defaultClock();
            this.filter = MetricFilter.ALL;
        }

        /**
         * Format numbers for the given {@link Locale}.
         *
         * @param locale a {@link Locale}
         * @return {@code this}
         */
        public DBReporter.Builder formatFor(Locale locale) {
            this.locale = locale;
            return this;
        }

        /**
         * Convert rates to the given time unit.
         *
         * @param rateUnit a unit of time
         * @return {@code this}
         */
        public DBReporter.Builder convertRatesTo(TimeUnit rateUnit) {
            this.rateUnit = rateUnit;
            return this;
        }

        /**
         * Convert durations to the given time unit.
         *
         * @param durationUnit a unit of time
         * @return {@code this}
         */
        public DBReporter.Builder convertDurationsTo(TimeUnit durationUnit) {
            this.durationUnit = durationUnit;
            return this;
        }

        /**
         * Use the given {@link Clock} instance for the time.
         *
         * @param clock a {@link Clock} instance
         * @return {@code this}
         */
        public DBReporter.Builder withClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        /**
         * Only report metrics which match the given filter.
         *
         * @param filter a {@link MetricFilter}
         * @return {@code this}
         */
        public DBReporter.Builder filter(MetricFilter filter) {
            this.filter = filter;
            return this;
        }

        /**
         * Builds a {@link CsvReporter} with the given properties, writing {@code .csv} files to the
         * given directory.
         *
         * @param directory the directory in which the {@code .csv} files will be created
         * @return a {@link CsvReporter}
         */
//        public DBReporter build(File directory) {
//            return new DBReporter(registry,
//                    locale,
//                    rateUnit,
//                    durationUnit,
//                    clock,
//                    filter);
//        }
    }
    @Override
    public void report(SortedMap<String, Gauge> gauges, SortedMap<String, Counter> counters, SortedMap<String, Histogram> histograms, SortedMap<String, Meter> meters, SortedMap<String, Timer> timers) {

    }
}
