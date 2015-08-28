package btrace.test.script;

import com.sun.btrace.BTraceUtils;
import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.Profiler;
import com.sun.btrace.annotations.*;

@BTrace class BTraceScript {
	@Property
	Profiler profiler = BTraceUtils.Profiling.newProfiler();
	final String clazzTest = "/btrace\\.test\\.verifyError\\..*/";

	@OnMethod(clazz=clazzTest, method="/.*/")
	void entry(@ProbeMethodName(fqn=true) String probeMethod)
	{
		BTraceUtils.Profiling.recordEntry(profiler, probeMethod);
	}

	@OnMethod(clazz=clazzTest, method="/.*/", location=@Location(value=Kind.RETURN))
	void exit(@ProbeMethodName(fqn=true) String probeMethod, @Duration long duration)
	{
		BTraceUtils.Profiling.recordExit(profiler, probeMethod, duration);
	}

	@OnExit
	public void printProfiler(int code)
	{
		BTraceUtils.Profiling.printSnapshot("Performance profile", profiler);
	}
}
