 package service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Blog;

public class CrudOperationusingSet {
	HashSet<Blog> list=new LinkedHashSet<Blog>(); // Store the blog in the list
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		CrudOperationusingSet  crud = new CrudOperationusingSet();
		boolean flag = true;
		while(flag) {
		System.out.println("Select opeation 1.create 2.view 3.update 4.delete 5.sort by title 6.sort by description");
		int choice = sc.nextInt();
		sc.nextLine();
		switch(choice) {
		case 1:	String title = sc.nextLine();
				String description = sc.nextLine();
				Blog blog = new Blog(title,description,LocalDate.now());
				crud.createBlog(blog);
					break;
		case 2:crud.viewBlog();
				break;
		case 3: String updatetitle = sc.nextLine();
				String updatedescription = sc.nextLine();
				Blog updatedblog = new Blog(updatetitle,updatedescription,LocalDate.now());
				crud.updateBlog(updatedblog);
				break;
		case 4: String blogtodelete = sc.nextLine();
		        crud.deleteBlog(blogtodelete);
		        break;
		case 5:List<Blog> sorted = crud.sortByTitle();
		        for(int  i =0;i < sorted.size();i++) {
		        	System.out.println(sorted.get(i).getBlogTitle()+" "+sorted.get(i).getBlogDescription());
		        }
		        break;
		case 6:
			List<Blog> sorted1 =  crud.sortByDescription();
			 for(int  i =0;i < sorted1.size();i++) {
		        	System.out.println(sorted1.get(i).getBlogTitle()+" "+sorted1.get(i).getBlogDescription());
		        }
			break;
		default:
			System.out.println("Bye Bye");
			flag = false;
		break;
		}
		}
		
	}
	public void createBlog(Blog blog) throws IOException {
		list.add(blog);
	}
	
	public void viewBlog() throws IOException{
		list.forEach((Blog blog) -> {System.out.println(blog.getBlogTitle()+" "+blog.getBlogDescription());});
	}
	
	public List<Blog> sortByTitle(){
	  List<Blog> sorted = new ArrayList<Blog>(list);
		sorted.sort((Blog blog1,Blog blog2) -> blog1.getBlogTitle().compareTo(blog2.getBlogTitle()));
	    return sorted;
	}
	
	public List<Blog> sortByDescription(){
		List<Blog> sorted = new ArrayList<Blog>(list);
		sorted.sort((Blog blog1,Blog blog2) -> blog1.getBlogDescription().compareTo(blog2.getBlogDescription()));
	    return sorted;
	}
	
	public HashSet<Blog> updateBlog(Blog blog) throws IOException{
		Iterator iterator = list.iterator();
		while(iterator.hasNext()) {
			Blog prev = (Blog) iterator.next();
			if(prev.getBlogTitle().equals(blog.getBlogTitle())) {
				prev.setBlogDescription(blog.getBlogDescription());
				prev.setDate(blog.getDate());
				break;
			}
		}
		return list;
	
	}
	public HashSet<Blog> deleteBlog(String title) throws IOException{
		Iterator iterator = list.iterator();
		while(iterator.hasNext()) {
			Blog prev = (Blog) iterator.next();
			if(prev.getBlogTitle().equals(title)) {
				list.remove(prev);
				break;
			}
		}
		return list;
	}
}
