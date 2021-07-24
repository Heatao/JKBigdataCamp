package mrDemo;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

// 偏移量，一行，电话号码，输入的Bean
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    private Text outKey = new Text();
    private FlowBean outV = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] items = line.split("\\s+");

        String phone = items[1];
        String upFlow = items[items.length-3];
        String downFLow = items[items.length-2];

        outKey.set(phone);

        outV.setUpFlow(Long.parseLong(upFlow));
        outV.setDownFlow(Long.parseLong(downFLow));
        outV.setSumFlow();
        context.write(outKey, outV);
    }
}
