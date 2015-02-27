//package dk.cphbusiness.hello;
//
//import dk.cphbusiness.entities.Teacher;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ControllerTest {
//
//    public static void main(String[] args) {
//        Controller controller = new Controller();
//        List <Teacher> t1 = new ArrayList<>();
//        t1.add(new Teacher("1"));
//        t1.add(new Teacher("2"));
//        controller.addProposal("Android", "make more apps", t1);
//        controller.addProposal("C#", "make good apps", t1);
//        
//        for (int i = 0; i < controller.getProposedSubjects().size(); i++) {
//            System.out.println("Proposed: " + controller.getProposedSubjects().get(i).getTitle());
//        }
//        
//        controller.AddToFirstRound(controller.getProposedSubjects().get(0).getTitle());
//        
//        for (int i = 0; i < controller.getFirstRound().size(); i++) {
//            System.out.println("FirstRound: " + controller.getFirstRound().get(i).toString());
//        }
//    }
//    
//}
