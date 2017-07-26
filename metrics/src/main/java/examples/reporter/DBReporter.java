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
 * 个人建议用file方式保存，后台写程序入库或者hdfs
 */
public class DBReporter extends ScheduledReporter {
    protected DBReporter(MetricRegistry registry,Locale locale,
                         Clock clock,
                         TimeZone timeZone,
                         TimeUnit rateUnit,
                         TimeUnit durationUnit,
                         MetricFilter filter,
                         LogDao logDao) {
        super(registry, "db-log", filter, rateUnit, durationUnit);
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

        @Override
        public String toString() {
            return "Builder{" +
                    "registry=" + registry +
                    ", locale=" + locale +
                    ", rateUnit=" + rateUnit +
                    ", durationUnit=" + durationUnit +
                    ", clock=" + clock.getTime() +
                    ", filter=" + filter +
                    '}';
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

    public static void main(String[]args){
        Builder builder = new Builder(new MetricRegistry());
        System.out.println(builder.toString());
    }
}
