using UnityEngine;
using System.Collections;
using UnityEditor;
public class card : MonoBehaviour {
	public Material Change2Mat;
	private Material normalMat;
	public Material touchMat;
	
	public enum Face{Ace,Two,Three,Four,Five,Six,Seven,Eight,Nine,Jack,Queen,King}
	public enum Suite{Heart,Spade,Diamond,Club,Back}
	public Face face;
	public Suite suite;
	
	private int cardtoplay;

	public bool active;
	// Use this for initialization
	void Start () {
		normalMat = this.GetComponent<MeshRenderer> ().material;
		active = true;
		//setCard (Face.Ace, Suite.Heart);
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	void OnCollisionEnter (Collision col)
	{

		GameObject.Find("hand"+cardtoplay%4+1).GetComponent<CardScript>().setCard(CardScript.Face.Four,CardScript.Suite.Club);
		cardtoplay++;


	}
	void OnCollisionExit(Collision col){
		if (active) {
			if (col.gameObject.transform.root.name == "RigidHand(Clone)") {
				this.GetComponent<MeshRenderer> ().material = normalMat;
				
				
			}
		}
	}
	void OnTriggerExit(Collider o){
		
		if (active) {
			if (o.gameObject.transform.root.name == "RigidHand(Clone)") {
				
				setCard(Face.Four,Suite.Back);
			}
		}
		
	}
	void OnTriggerEnter(Collider o){
		if (active) {
			
			if (o.gameObject.transform.root.name == "RigidHand(Clone)") {
				
				setCard(Face.Four,Suite.Diamond);
			}
		}
	}
	
	void setCard(CardScript.Face f, CardScript.Suite s ) {
		face = f;
		suite = s;
		string matName = "";
		
		if (s == Suite.Back) {
			Material card = AssetDatabase.LoadAssetAtPath ("Assets/52 Playing Cards/Materials/backside.mat", typeof(Material)) as Material;
			GetComponent<MeshRenderer> ().material = card;
		} else {
			switch (suite) {
			case Suite.Club:
				matName += "c";
				break;
			case Suite.Diamond:
				matName += "d";
				break;
			case Suite.Heart:
				matName += "h";
				break;
			case Suite.Spade:
				matName += "s";
				break;
			}
			
			switch (face) {
			case Face.Ace:
				matName += "_a";
				break;
			case Face.Two:
				matName += "_02";
				break;
			case Face.Three:
				matName += "_03";
				break;
			case Face.Four:
				matName += "_04";
				break;
			case Face.Five:
				matName += "_05";
				break;
			case Face.Six:
				matName += "_06";
				break;
			case Face.Seven:
				matName += "_07";
				break;
			case Face.Eight:
				matName += "_08";
				break;
			case Face.Nine:
				matName += "_09";
				break;
			case Face.Jack:
				matName += "_j";
				break;
			case Face.Queen:
				matName += "_q";
				break;
			case Face.King:
				matName += "_k";
				break;
			}
			Material card = AssetDatabase.LoadAssetAtPath ("Assets/52 Playing Cards/Materials/" + matName + ".mat", typeof(Material)) as Material;
			GetComponent<MeshRenderer> ().material = card;
		}
	}
}
