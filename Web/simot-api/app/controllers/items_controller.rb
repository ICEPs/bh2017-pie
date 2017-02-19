class ItemsController < ApplicationController
  before_action :set_items, only: [:show, :update, :destroy]

  def index
    @items = Item.all

    if params[:filter]
      case params[:filter]

      when "name"
        render json: get_by_name
      when "company_name"
        render json: get_by_company_name
      when "urgency"
        render json: get_by_urgency
      when "post_author"
        render json: get_by_author
      end
    else
      render json: @items
    end
  end

  def create
    @item = Item.new
    @item.item_name = params[:item_name]
    @item.item_description = params[:item_description]
    @item.company_name = params[:company_name]
    @item.urgency = params[:urgency]
    @item.expiry_date = Time.at params[:expiry_date].to_f
    @item.donation_title = params[:donation_title]
    @item.post_author_id = params[:post_author_id]

    if @item.save
      render json: @item, status: :created, location: @item
    else
      render json: @item.errors, status: :unprocessable_entity
    end
  end

  def show
    render json: @items
  end

  def update
    @item = Deliverable.find(params[:id])

    if @item.update(item_params)
      head :no_content
    else
      render json: @item.errors, status: :unprocessable_entity
    end
  end

  def destroy
    @item.destroy

    head :no_content
  end



  private

    def set_items
      @items = Item.find(params[:id])
    end

    def item_params
      params.require(:item).permit!
    end

    def get_by_name
      Item.order(:item_name)
    end

    def get_by_company_name
      Item.order(:company_name)
    end

    def get_by_urgency
      Item.order(:urgency)
    end

    def get_by_author
      Donations.order(:post_author_id)
    end

end
