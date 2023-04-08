package ir.ha.practice.ui.tabs.samples_tab.hilt


//@AndroidEntryPoint
//class HiltFrg :  BaseFragment<FragmentHiltBinding>() {
//    override val layoutId: Int
//        get() = R.layout.fragment_hilt
//
//    @Inject
//    lateinit var testClass2 : TestClass2
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        showToast(requireContext(),testClass.logTestMessage())
//        showToast(requireContext(),testClass2.logTestMessage2())
//    }
//
//}
//
//
//
//class TestClass
//@Inject
//constructor(){
//
//    fun logTestMessage() : String = "this is test msg"
//
//}
//
//@ActivityScoped
//class TestClass2
//@Inject
//constructor(private val testCls : TestClass){
//    fun logTestMessage2() : String = testCls.logTestMessage()
//}